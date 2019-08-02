import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Radostin Dimkov on 01.08.19
 */
class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    LinkedHashMap<String, Map<String, Integer>> venues = new LinkedHashMap<>();
    Pattern venuePattern = Pattern.compile("^([\\w\\s]+)\\s@([\\w\\s]*)\\s([\\d]*)\\s([\\d]*)$");
    while (true) {
      String input = reader.readLine();
      if (input.equalsIgnoreCase("End")) {
        break;
      }

      Matcher matcher = venuePattern.matcher(input);
      if (matcher.matches()) {
        String singer = matcher.group(1);
        String venue = matcher.group(2);
        int ticketsPrice = Integer.parseInt(matcher.group(3));
        int ticketsCount = Integer.parseInt(matcher.group(4));

        if (!venues.containsKey(venue)) {
          venues.put(venue, new LinkedHashMap<>());
        }

        if (!venues.get(venue).containsKey(singer)) {
          venues.get(venue).put(singer, 0);
        }

        venues.get(venue).put(singer, (ticketsCount * ticketsPrice) + (venues.get(venue).get(singer)));
      }
    }

    venues.forEach((key1, value1) -> {
      System.out.println(key1);
      value1 = sortByValues(value1);
      value1.forEach((key, value) -> System.out.println("#  " + key + " -> " + value));
    });
  }

  public static Map<String, Integer> sortByValues(Map<String,Integer> map){
    List<Entry<String, Integer>> sortedList = new ArrayList<>(map.entrySet());
    sortedList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

    return sortedList.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a,b) -> b, LinkedHashMap::new));
  }
}