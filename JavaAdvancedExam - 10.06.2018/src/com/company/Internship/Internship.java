package com.company.Internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Internship {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Deque<String> problems = new ArrayDeque<>();
        Deque<String> candidates = new ArrayDeque<>();

        int problemsCount = Integer.parseInt(reader.readLine());
        int candidatesCount = Integer.parseInt(reader.readLine());

        String validateUsernamesRegex = "^[A-Z][a-z]+ [A-Z][a-z]+$";

        for (int i = 0; i < problemsCount; i++) {
            String input = reader.readLine();
            problems.push(input);
        }

        for (int i = 0; i < candidatesCount; i++) {
            String input = reader.readLine();
            if(input.matches(validateUsernamesRegex)){
                candidates.offer(input);
            }
        }

        while(true){
            if(candidates.isEmpty())
                break;
            if(problems.isEmpty()){
                int candidatesSize = candidates.size();
                for (int i = 0; i < candidatesSize; i++) {
                    if(i == candidatesSize - 1){
                        System.out.printf("%s",candidates.poll());
                    }
                    else {
                        System.out.printf("%s, ",candidates.poll());
                    }
                }
                break;
            }

            if(candidates.size() == 1){
                System.out.printf("%s gets the job!%n",candidates.poll());
            }

            String candidate = candidates.poll();
            String problem = problems.pop();

            if(candidate == null)
                break;
            if(problem == null)
                break;

            int candidateCharsSum = candidate.chars().sum();
            int problemCharsSum = problem.chars().sum();

            //the problem is solved
            if(candidateCharsSum > problemCharsSum){
                candidates.offer(candidate);
                System.out.printf("%s solved %s.%n",candidate,problem);
            }
            //the problem is unsolved
            else{
                problems.offer(problem);
                System.out.printf("%s failed %s.%n",candidate,problem);
            }
        }

    }
}