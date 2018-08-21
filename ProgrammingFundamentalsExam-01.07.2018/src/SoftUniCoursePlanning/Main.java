package SoftUniCoursePlanning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<String> lessonsAndExercise = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] scheduleLessons = reader.readLine().split(", ");
        //add lessons

        for (String currentLesson : scheduleLessons) {
            lessonsAndExercise.add(currentLesson);
        }


        while (true) {
            String input = reader.readLine();

            if (input.equalsIgnoreCase("course start"))
                break;

            String[] str = input.split(":");

            String command = str[0];
            String[] params = Arrays.stream(str).skip(1).toArray(String[]::new);

            if (command.equalsIgnoreCase("Add")) {
                String lessonTitle = params[0];
                if (!ifLessonExists(lessonsAndExercise, lessonTitle)) {
                    lessonsAndExercise.add(lessonTitle);
                }
            } else if (command.equalsIgnoreCase("Insert")) {
                String lessonTitle = params[0];
                int index = Integer.parseInt(params[1]);

                //if not exists && and is index is valid
                if (!ifLessonExists(lessonsAndExercise, lessonTitle) && index >= 0) {
                    lessonsAndExercise.add(index, lessonTitle);
                }
            } else if (command.equalsIgnoreCase("Remove")) {
                String lessonTitle = params[0];

                if (ifLessonExists(lessonsAndExercise, lessonTitle)) {
                    lessonsAndExercise.remove(lessonTitle);
                }
                if (ifExerciseExists(lessonsAndExercise, lessonTitle)) {
                    String lessonExercise = lessonTitle + "-Exercise";
                    lessonsAndExercise.remove(lessonExercise);
                }
            } else if (command.equalsIgnoreCase("Swap")) {
                String firstLessonTitle = params[0];
                String secondLessonTitle = params[1];

                //if lessons exists
                if(ifLessonExists(lessonsAndExercise,firstLessonTitle)
                        && ifLessonExists(lessonsAndExercise,secondLessonTitle)){
                    int firstLessonIndex = getIndex(lessonsAndExercise,firstLessonTitle);
                    int secondLessonIndex = getIndex(lessonsAndExercise,secondLessonTitle);

                    Collections.swap(lessonsAndExercise,firstLessonIndex,secondLessonIndex);

                    if(ifExerciseExists(lessonsAndExercise,firstLessonTitle)){
                        int lessonIndex = secondLessonIndex + 1;
                        lessonsAndExercise.remove(getIndex(lessonsAndExercise,firstLessonTitle + "-Exercise"));
                        lessonsAndExercise.add(lessonIndex,firstLessonTitle + "-Exercise");
                    }
                    if(ifExerciseExists(lessonsAndExercise,secondLessonTitle)){
                        int lessonIndex = firstLessonIndex + 1;
                        lessonsAndExercise.remove(getIndex(lessonsAndExercise,secondLessonTitle + "-Exercise"));
                        lessonsAndExercise.add(lessonIndex,secondLessonTitle + "-Exercise");
                    }
                }



            } else if (command.equalsIgnoreCase("Exercise")) {
                String lessonTitle = params[0];

                //if the lesson exists and there is no exercise already
                if (ifLessonExists(lessonsAndExercise, lessonTitle)
                        && !ifExerciseExists(lessonsAndExercise, lessonTitle)) {
                    String exerciseName = lessonTitle + "-Exercise";
                    lessonsAndExercise.add(lessonsAndExercise.indexOf(lessonTitle) + 1,exerciseName);
                }else if(!ifExerciseExists(lessonsAndExercise,lessonTitle)
                        && !ifExerciseExists(lessonsAndExercise,lessonTitle)){
                    String exerciseName = lessonTitle + "-Exercise";
                    lessonsAndExercise.add(lessonTitle);
                    lessonsAndExercise.add(exerciseName);
                }
            }


        }

        for (int i = 0; i < lessonsAndExercise.size(); i++) {
            System.out.printf("%d.%s%n",i + 1,lessonsAndExercise.get(i));
        }
    }


    private static boolean ifLessonExists(List<String> lessons, String lessonTitle) {
        for (String currentLesson : lessons) {
            if (currentLesson.equalsIgnoreCase(lessonTitle))
                return true;
        }
        return false;
    }

    private static boolean ifExerciseExists(List<String> lessonsAndExercises, String lessonTitle) {
        for (String title : lessonsAndExercises) {
            if (title.contains("-")) {
                String[] str = title.split("-");

                if (str[1].equalsIgnoreCase("Exercise")
                        && str[0].equalsIgnoreCase(lessonTitle)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getIndex(List<String> lessonsAndExercise, String title){
        int index = -1;
        for (String currentElement : lessonsAndExercise) {
            index++;
            if(currentElement.equalsIgnoreCase(title)){
                break;
            }
        }
        return index;
    }
}


