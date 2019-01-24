package com.company.Genome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Genome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<Integer>> genomes = new LinkedHashMap<>();

        String regex = "^([(!@#$?a-zA-Z]+)=([0-9]+)--([0-9]+)<<([a-zA-Z]+)$";
        Pattern pattern = Pattern.compile(regex);

        while (true) {
            String input = reader.readLine();
            if (input.equalsIgnoreCase("Stop!"))
                break;

            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                //count first name letters
                String alphabet = "abcdefghijklmnopqrstuvwxyz";

                String name = matcher.group(1);
                int nameCounter = 0;

                for (int i = 0; i < name.length(); i++) {
                    if(alphabet.contains(Character.toString(name.charAt(i))))
                        nameCounter++;
                }



                int allowedLenght = Integer.parseInt(matcher.group(2));
                int countOfGenes = Integer.parseInt(matcher.group(3));
                String nameOfGene = matcher.group(4);

                //fill map
                if (nameCounter == allowedLenght ) {
                    // if dont exist
                    if (!genomes.containsKey(nameOfGene)) {
                        List<Integer> list = new LinkedList<>();
                        list.add(countOfGenes);
                        genomes.put(nameOfGene, list);
                    } else {
                        genomes.get(nameOfGene).add(countOfGenes);
                    }
                }
            }
        }

        Map<String,Integer> map = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> currentGenome : genomes.entrySet()) {
            List<Integer> list = currentGenome.getValue();
            int totalSum = 0;
            for (Integer integer : list) {
                totalSum+=integer;
            }
            if(!map.containsKey(currentGenome)){
                map.put(currentGenome.getKey(),totalSum);
            }else {
                map.replace(currentGenome.getKey(),totalSum);
            }
            totalSum = 0;
        }

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

        for (Map.Entry<String, Integer> current : aMap2.entrySet()) {
            System.out.printf("%s has genome size of %d%n",current.getKey(),current.getValue());
        }
    }
}
