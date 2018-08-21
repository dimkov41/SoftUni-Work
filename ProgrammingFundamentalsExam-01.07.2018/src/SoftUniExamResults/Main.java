package SoftUniExamResults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<User> users = new LinkedList<>();
    private static List<Submission> submissions = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String input = reader.readLine();

            if("exam finished".equalsIgnoreCase(input))
                break;

            String[] str = input.split("-");

            String username = str[0];
            if(str[1].equalsIgnoreCase("banned")){
                if(ifUserExists(username)){
                    User user = getUser(username);
                    users.remove(user);
                }

            }else {
                String language = str[1];
                int points = Integer.parseInt(str[2]);

                //if user exists
                if (ifUserExists(username)) {
                    User user = getUser(username);
                    if (user.getPoints() < points) {
                        user.setPoints(points);
                    }
                } else {
                    User user = new User(username, points);
                    users.add(user);
                }

                //if submission exists
                if (ifSubmissionExists(language)) {
                    Submission submission = getSubmission(language);
                    submissions.get(submissions.indexOf(submission))
                            .setSubmissionsCount(submissions.get(submissions.indexOf(submission)).getSubmissionsCount() + 1);
                } else {
                    Submission submission = new Submission(language);
                    submissions.add(submission);
                }
            }

        }
        Collections.sort(users, (o1, o2) -> {
            if(Integer.compare(o2.getPoints(),o1.getPoints()) == 0)
                return o1.getUsername().compareTo(o2.getUsername());
            return Integer.compare(o2.getPoints(),o1.getPoints());
        });

        Collections.sort(submissions,(o1,o2) -> {
            if(Integer.compare(o2.getSubmissionsCount(),o1.getSubmissionsCount()) == 0)
                return o1.getLanguage().compareTo(o2.getLanguage());
            return Integer.compare(o2.getSubmissionsCount(),o1.getSubmissionsCount());
        });


        System.out.println("Results:");
        for (User user : users) {
            System.out.printf("%s | %d%n",user.getUsername(),user.getPoints());
        }

        System.out.println("Submissions:");
        for (Submission submission : submissions) {
            System.out.printf("%s - %d%n",submission.getLanguage(),submission.getSubmissionsCount());
        }
    }

    private static class User{
        private String username;
        private int points;

        User(String username, int points) {
            this.username = username;
            this.points = points;
        }

        String getUsername() {
            return username;
        }

        int getPoints() {
            return points;
        }

        void setPoints(int points) {
            this.points = points;
        }
    }

    private static class Submission{
        private String language;
        private  int submissionsCount;

        Submission(String language) {
            this.language = language;
            this.submissionsCount = 1;
        }

        String getLanguage() {
            return language;
        }

        int getSubmissionsCount() {
            return submissionsCount;
        }

        void setSubmissionsCount(int submissionsCount) {
            this.submissionsCount = submissionsCount;
        }
    }


    private static boolean ifUserExists(String userName){
        for (User currentUser : users) {
            if(currentUser.getUsername().equalsIgnoreCase(userName))
                return true;
        }
        return false;
    }

    private static User getUser(String username){
        for (User currentUser : users) {
            if(currentUser.getUsername().equalsIgnoreCase(username))
                return currentUser;
        }
        return null;
    }

    private static boolean ifSubmissionExists(String language){
        for (Submission currentSubmission : submissions) {
            if(currentSubmission.getLanguage().equalsIgnoreCase(language))
                return true;
        }
        return false;
    }

    private static Submission getSubmission(String language){
        for (Submission currentSubmission : submissions) {
            if(currentSubmission.getLanguage().equalsIgnoreCase(language))
                return currentSubmission;
        }
        return null;
    }
}
