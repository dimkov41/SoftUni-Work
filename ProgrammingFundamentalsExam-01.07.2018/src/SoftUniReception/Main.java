package SoftUniReception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //number of students per hour
        int firstEmployeeEfficiency = Integer.parseInt(reader.readLine());
        int secondEmployeeEfficency = Integer.parseInt(reader.readLine());
        int thirdEmployeeEfficency = Integer.parseInt(reader.readLine());

        int studentsCount = Integer.parseInt(reader.readLine());

        int totalStudentAnsweredInHour = firstEmployeeEfficiency + secondEmployeeEfficency + thirdEmployeeEfficency;

        int hours = 0;

        while (studentsCount > 0) {
            studentsCount -= totalStudentAnsweredInHour;
            hours++;
            if (hours % 4 == 0)
                hours++;
        }
        System.out.printf("Time needed: %dh.", hours);
    }
}
