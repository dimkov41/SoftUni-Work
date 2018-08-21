package SoftUniBarIncome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<Customer> customers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String input = reader.readLine();

            if("end of shift".equalsIgnoreCase(input))
                break;

            Pattern pattern = Pattern
                    .compile("%([A-Z][a-z]+)%([^\\|\\$%.]+|)<([\\w]+)>([^\\|\\$%.]+|)\\|([0-9]+)\\|([^\\|\\$%.\\d]+|)([0-9.]+)\\$");

            Matcher matcher = pattern.matcher(input);
            if(matcher.matches()){
                String customerName = matcher.group(1);
                String productName = matcher.group(3);
                int quantity = Integer.parseInt(matcher.group(5));
                double price = Double.parseDouble(matcher.group(7));

                Customer customer = new Customer(customerName,new Product(productName,quantity,price));
                customers.add(customer);
            }

        }

        double totalIncome = 0;
        for (Customer currentCustomer : customers) {
            System.out.printf("%s: %s - %.2f%n",
                    currentCustomer.getName(),
                    currentCustomer.getProduct().getName(),
                    currentCustomer.getProduct().getTotalPrice());
            totalIncome += currentCustomer.getProduct().getTotalPrice();
        }

        System.out.printf("Total income: %.2f",totalIncome);
    }

    private static class Customer{
        private String name;
        private Product product;

        public Customer(String name) {
            this.name = name;
        }

        Customer(String name, Product product) {
            this.name = name;
            this.product = product;
        }

        String getName() {
            return name;
        }

        Product getProduct() {
            return product;
        }
    }

    private static class Product{
        private String name;
        private int quantity;
        private double price;

        public Product(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalPrice() {
            return quantity * price;
        }
    }
}
