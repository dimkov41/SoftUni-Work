import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author Radostin Dimkov on 01.08.19
 */
class SerbianUnleashed {
    static void main(String[] args) {
        BufferedReader reader = System.in.newReader()

        LinkedHashMap<String, Map<String, Integer>> venues = [:]
        Pattern venuePattern = ~/^([\w\s]+)\s@([\w\s]*)\s([\d]*)\s([\d]*)$/
        while (true) {
            String input = reader.readLine()
            if (input == "End") {
                break
            }

            if (input ==~ venuePattern) {
                Matcher matcher = input =~ venuePattern
                assert matcher.matches()
                String singer = matcher.group(1)
                String venue = matcher.group(2)
                int ticketsPrice = matcher.group(3) as int
                int ticketsCount = matcher.group(4) as int

                if (!venues.containsKey(venue)) {
                    venues.put(venue, [:]);
                }

                if (!venues.get(venue).containsKey(singer)) {
                    venues.get(venue)[singer] = 0
                }

                venues.get(venue)[singer] = (ticketsCount * ticketsPrice) + (venues.get(venue).get(singer))
            }
        }

        venues.each { venue, values ->
            println venue
            values.sort({ -it.value }).each { singer, amount ->
                println "#  $singer -> $amount"
            }
        }
    }
}