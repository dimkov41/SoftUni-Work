package com.company.Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int matrixSize = Integer.parseInt(reader.readLine());
        String cm = reader.readLine().trim();
        String[] commands = cm.replaceAll(" ", "").split(",");

        String[][] matrix = new String[matrixSize][matrixSize];

        //start position of snake
        int startRow = 0;
        int startCol = 0;

        int food = 0;

        if (matrixSize != 0) {
            //read input
            for (int i = 0; i < matrixSize; i++) {
                String[] input = reader.readLine().split(" ");

                if (matrixSize == input.length) {
                    for (int col = 0; col < matrixSize; col++) {
                        matrix[i][col] = input[col];
                        if (input[col].equals("s")) {
                            startRow = i;
                            startCol = col;
                        }
                        if (input[col].equals("f")) {
                            food++;
                        }
                    }
                }
            }

            int snakeLenght = 1;
            boolean isBreaked = false;

            outerLoop:
            for (int i = 0; i < commands.length; i++) {
                if (commands[i].equalsIgnoreCase("left")) {
                    //if is last position
                    if (startCol == 0) {
                        startCol = matrix[0].length - 1;
                    } else {
                        startCol--;
                    }


                    if ("f".equals(matrix[startRow][startCol])) {
                        snakeLenght++;
                        food--;
                        matrix[startRow][startCol] = "*";
                    } else if ("e".equals(matrix[startRow][startCol])) {
                        System.out.println("You lose! Killed by an enemy!");
                        isBreaked = true;
                        break outerLoop;
                    }

                } else if (commands[i].equalsIgnoreCase("down")) {
                    // if is last row
                    if (startRow == matrix.length - 1) {
                        startRow = 0;
                    } else {
                        //go to next row if have it
                        startRow++;
                    }

                    if ("f".equals(matrix[startRow][startCol])) {
                        snakeLenght++;
                        food--;
                        matrix[startRow][startCol] = "*";
                    } else if ("e".equals(matrix[startRow][startCol])) {
                        System.out.println("You lose! Killed by an enemy!");
                        isBreaked = true;
                        break outerLoop;
                    }

                } else if (commands[i].equalsIgnoreCase("right")) {
                    //if last position in col
                    if (startCol == matrix[startRow].length - 1) {
                        startCol = 0;
                    } else {
                        startCol++;
                    }

                    if ("f".equals(matrix[startRow][startCol])) {
                        snakeLenght++;
                        food--;
                        matrix[startRow][startCol] = "*";
                    } else if ("e".equals(matrix[startRow][startCol])) {
                        System.out.println("You lose! Killed by an enemy!");
                        isBreaked = true;
                        break outerLoop;
                    }

                } else if (commands[i].equalsIgnoreCase("up")) {
                    //check if is firstRow
                    if (startRow == 0) {
                        startRow = matrix.length - 1;
                    } else {
                        startRow--;
                    }

                    if ("f".equals(matrix[startRow][startCol])) {
                        snakeLenght++;
                        food--;
                        matrix[startRow][startCol] = "*";
                    } else if ("e".equals(matrix[startRow][startCol])) {
                        System.out.println("You lose! Killed by an enemy!");
                        isBreaked = true;
                        break outerLoop;
                    }

                }
            }


            if (!isBreaked) {
                if (food > 0) {
                    System.out.printf("You lose! There is still %d food to be eaten.", food);
                } else {
                    System.out.printf("You win! Final snake length is %d", snakeLenght);
                }
            }
        }
    }
}