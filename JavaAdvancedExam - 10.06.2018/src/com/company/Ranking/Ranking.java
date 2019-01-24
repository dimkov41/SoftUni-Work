package com.company.Ranking;

import javafx.scene.web.WebHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Ranking {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String,String> contests = new LinkedHashMap<>();
        Map<String,Map<String,Integer>> candidates = new LinkedHashMap<>();

        //fill contests map
        while(true){
            String input = reader.readLine();
            if(input.equalsIgnoreCase("end of contests"))
                break;

            String[] array= input.split(":");

            //fill map
            contests.put(array[0],array[1]);
        }

        //fill submissions map
        if(!contests.isEmpty()) {
            while (true) {
                String input = reader.readLine();
                if(input.equalsIgnoreCase("end of submissions"))
                    break;

                String[] array = input.trim().split("=>");

                String contest = array[0];
                String contestPassword = array[1];
                String candidateName = array[2];
                Integer points = Integer.parseInt(array[3]);

                /////////if jugde don't give me full points, I will try to use equalsIgroneCase -_- then will break my laptop.
                if(contestPassword.equals(contests.get(contest))){
                    //if dont contains candidate
                    if(!candidates.containsKey(candidateName)){
                        Map<String,Integer> map = new LinkedHashMap<>();
                        map.put(contest,points);

                        candidates.put(candidateName,map);
                    }
                    // if exists
                    else {
                        Map<String,Integer> map = candidates.get(candidateName);
                        // if have given contest
                        if(map.containsKey(contest)){
                            int currentPoints = map.get(contest);

                            if(currentPoints < points){
                                map.replace(contest,points);
                            }
                        }
                        else{
                            map.put(contest,points);
                        }

                        candidates.replace(candidateName,map);
                    }

                }

            }
        }

        int bestPoint = 0;
        String bestPerson = null;

        int currentPoints = 0;

        // find person with greatest points
        for (Map.Entry<String, Map<String, Integer>> currentCandidate : candidates.entrySet()) {
            for (Integer integer : currentCandidate.getValue().values()) {
                currentPoints += integer;
            }
            if(bestPoint < currentPoints){
                bestPerson = currentCandidate.getKey();
                bestPoint = currentPoints;
            }
            currentPoints=0;
        }

        System.out.printf("Best candidate is %s with total %d points.%n",bestPerson,bestPoint);
        System.out.println("Ranking:");

        for (String currentPerson : candidates.keySet().stream()
                .sorted(String::compareTo).collect(Collectors.toList())) {
            System.out.println(currentPerson);
            Map<String,Integer> map = candidates.get(currentPerson);

            Set<Map.Entry<String,Integer>> mapEntries = map.entrySet();
            List<Map.Entry<String,Integer>> aList = new LinkedList<>(mapEntries);

            Collections.sort(aList, new Comparator<Map.Entry<String,Integer>>() {


                @Override

                public int compare(Map.Entry<String, Integer> ele1,

                                   Map.Entry<String, Integer> ele2) {



                    return ele2.getValue().compareTo(ele1.getValue());

                }

            });

            Map<String,Integer> aMap2 = new LinkedHashMap<String, Integer>();
            for(Map.Entry<String,Integer> entry: aList) {
                aMap2.put(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, Integer> entry : aMap2.entrySet()) {
                System.out.printf("#  %s -> %d",entry.getKey(),entry.getValue());
                System.out.println();
            }
        }
    }
}
