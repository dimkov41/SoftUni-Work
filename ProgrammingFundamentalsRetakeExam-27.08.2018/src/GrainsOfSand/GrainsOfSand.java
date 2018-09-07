import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GrainsOfSand {
    private static List<Integer> grains = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\s+");
        for (String currentGrain : input) {
            grains.add(Integer.parseInt(currentGrain));
        }


        while (true) {
            String str = reader.readLine();

            if (str.equalsIgnoreCase("Mort"))
                break;

            String command = str.split("\\s+")[0];
            int value = Integer.parseInt(str.split("\\s+")[1]);

            if (command.equalsIgnoreCase("Add")) {
                grains.add(value);
            } else if (command.equalsIgnoreCase("Remove")) {
                remove(value);
            } else if (command.equalsIgnoreCase("Replace")) {
                int replacement = Integer.parseInt(str.split("\\s+")[2]);
                replace(value,replacement);
            } else if(command.equalsIgnoreCase("Increase")){
                increase(value);
            }else if(command.equalsIgnoreCase("Collapse")){
                collapse(value);
            }
        }

        for (Integer grain : grains) {
            System.out.printf("%d ",grain);
        }
    }

    private static void remove(int value){
        boolean ifSuchElementExists = false;

        for (int i = 0; i < grains.size(); i++) {
            int grain = grains.get(i);
            if(grain == value){
                grains.remove(i);
                ifSuchElementExists = true;
                break;
            }
        }

        if(!ifSuchElementExists){
            if(value < grains.size()){
                grains.remove(value);
            }
        }
    }
    private static void replace(int value,int replacement){
        for (int i = 0; i < grains.size(); i++) {
            int grain = grains.get(i);
            if(grain == value){
                grains.set(i,replacement);
                break;
            }
        }
    }
    private static void increase(int value){
        boolean ifSuchElementExists = false;

        for (int i = 0; i < grains.size(); i++) {
            int grain = grains.get(i);
            //remove = if not working in judge
            if(grain >= value){
                increaseValueOfAllElements(grain);
                ifSuchElementExists = true;
                break;
            }
        }

        if(!ifSuchElementExists){
            int grain = grains.get(grains.size() - 1);
            increaseValueOfAllElements(grain);
        }
    }

    private static void collapse(int value){
        List<Integer> valuesToRemove = new LinkedList<>();
        for (int i = 0; i < grains.size(); i++) {
            int grain = grains.get(i);

            if(grain < value)
                valuesToRemove.add(grain);
        }

        for (int i = 0; i < valuesToRemove.size(); i++) {
            int grainToRemove = valuesToRemove.get(i);
            grains.remove(grainToRemove);
        }
    }


    private static void increaseValueOfAllElements(int value){
        for (int i = 0; i < grains.size(); i++) {
            int grain = grains.get(i);
            grain += value;
            grains.set(i,grain);
        }
    }
}
