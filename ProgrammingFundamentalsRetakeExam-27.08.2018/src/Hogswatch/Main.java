import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfHomes = Integer.parseInt(reader.readLine());

        int presentsInHisBag = Integer.parseInt(reader.readLine());

        int initialPresents = presentsInHisBag;
        int additionalPresentsTaken = 0;
        int homeVisited = 0;
        int timesBack = 0;
        for (int i = 0; i < numberOfHomes; i++) {
            homeVisited++;
            int numberOfSocks = Integer.parseInt(reader.readLine());
            presentsInHisBag -= numberOfSocks;

            if(presentsInHisBag < 0){
                int remainingHomes = numberOfHomes - homeVisited;
                int additionalPresentsForCurrentHome = Math.abs(presentsInHisBag);
                int currentPresentsTaken = (initialPresents / homeVisited) * remainingHomes + additionalPresentsForCurrentHome;
                additionalPresentsTaken += currentPresentsTaken;
                presentsInHisBag += currentPresentsTaken;
                timesBack++;
            }
        }

        if(timesBack == 0){
            System.out.println(presentsInHisBag);
        }else if(timesBack > 0){
            System.out.println(timesBack);
            System.out.println(additionalPresentsTaken);
        }
    }
}
