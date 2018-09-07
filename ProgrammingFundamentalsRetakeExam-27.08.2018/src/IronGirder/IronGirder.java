import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class IronGirder {
    private static List<Train> trains = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = reader.readLine();

            if (str.equalsIgnoreCase("Slide rule"))
                break;

            String[] input = str.split("[:>]");

            String destination = input[0];
            int passegersCount = Integer.parseInt(input[2]);

            if (input[1].substring(0, input[1].length() - 1).equalsIgnoreCase("ambush")) {
                //if not exists ignore line
                if (ifDestinationExists(destination)) {
                    Train currentTrain = getTrain(destination);
                    currentTrain.setTime(Integer.MAX_VALUE);
                    currentTrain.setPassengersCount(currentTrain.getPassengersCount() - passegersCount);
                }
            } else {
                int time = Integer.parseInt(input[1].substring(0, input[1].length() - 1));
                //train travelled the distance
                if (ifDestinationExists(destination)) {
                    Train train = getTrain(destination);
                    if (train.getTime() > time)
                        train.setTime(time);
                    train.setPassengersCount(train.getPassengersCount() + passegersCount);
                } else {
                    Train currentTrain = new Train(destination, time, passegersCount);
                    trains.add(currentTrain);
                }
            }

        }

        removeThatHasNotArrivedOrHaveZeroPassangers();

        trains.sort((o1, o2) -> {
            int bestTimeComp = Integer.compare(o1.getTime(), o2.getTime());
            if (bestTimeComp == 0)
                return o1.getDestination().compareTo(o2.getDestination());
            return bestTimeComp;
        });

        //"{townName} -> Time: {fastestTime} -> Passengers: {totalCountPassengers}"
        for (Train currentTrain : trains) {
            System.out.printf("%s -> Time: %d -> Passengers: %d%n",
                    currentTrain.getDestination(),
                    currentTrain.getTime(),
                    currentTrain.getPassengersCount());
        }

    }

    public static class Train {
        private String destination;
        private int time;
        private int passengersCount;

        public Train(String destination, int time, int passengersCount) {
            this.destination = destination;
            this.time = time;
            this.passengersCount = passengersCount;
        }

        protected String getDestination() {
            return destination;
        }

        protected int getTime() {
            return time;
        }

        protected int getPassengersCount() {
            return passengersCount;
        }

        protected void setTime(int time) {
            this.time = time;
        }

        protected void setPassengersCount(int passengersCount) {
            this.passengersCount = passengersCount;
        }
    }

    private static boolean ifDestinationExists(String destination) {
        for (Train currentTrain : trains) {
            if (currentTrain.getDestination().equalsIgnoreCase(destination))
                return true;
        }
        return false;
    }

    private static Train getTrain(String destination) {
        for (Train train : trains) {
            if (train.getDestination().equalsIgnoreCase(destination))
                return train;
        }
        return null;
    }

    private static void removeThatHasNotArrivedOrHaveZeroPassangers() {
        for (int i = 0; i < trains.size(); i++) {
            Train currentTrain = trains.get(i);
            if (currentTrain.getTime() == 0 || currentTrain.getTime() == Integer.MAX_VALUE)
                trains.remove(currentTrain);
            else if (currentTrain.getPassengersCount() <= 0)
                trains.remove(currentTrain);
        }
    }
}
