import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Radostin Dimkov on 05.08.19
 */
class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int linesCount = Integer.parseInt(reader.readLine());

    Map<String, Map<String, Map<Value, Integer>>> types = new LinkedHashMap<>();
    while (linesCount-- > 0) {
      String[] input = reader.readLine().split("\\s+");
      String type = input[0];
      String name = input[1];
      Integer damage = parseInt(input[2]) == null ? Value.DAMAGE.getValue() : parseInt(input[2]);
      Integer health = parseInt(input[3]) == null ? Value.HEALTH.getValue() : parseInt(input[3]);
      Integer armor = parseInt(input[4]) == null ? Value.ARMOR.getValue() : parseInt(input[4]);

      if (!types.containsKey(type)) {
        types.put(type, new TreeMap<>());
      }

      //should override if existing dragon with new stats
      types.get(type).put(name, setValues(
          types.get(type).get(name) == null ? new LinkedHashMap<>()
              : types.get(type).get(name), damage, health, armor));
    }

    for (Entry<String, Map<String, Map<Value, Integer>>> entrySet : types.entrySet()) {
      double avgDamage = getAverage(entrySet.getValue(), Value.DAMAGE);
      double avgHealth = getAverage(entrySet.getValue(), Value.HEALTH);
      double avgArmor = getAverage(entrySet.getValue(), Value.ARMOR);

      String type = entrySet.getKey();
      System.out.println(
          String.format("%s::(%.2f/%.2f/%.2f)", entrySet.getKey(), avgDamage, avgHealth,
              avgArmor)
      );

      entrySet.getValue().forEach((dragon, stats) -> {
        System.out.println(String.format("-%s -> damage: %d, health: %d, armor: %d", dragon,
            stats.get(Value.DAMAGE), stats.get(Value.HEALTH), stats.get(Value.ARMOR)));
      });
    }
  }

  private static double getAverage(Map<String, Map<Value, Integer>> dragons, Value type) {
    int sum = 0;
    double i = 0;
    for (Entry<String, Map<Value, Integer>> entry : dragons.entrySet()) {
      sum += entry.getValue().get(type);
      i++;
    }
    return sum / i;
  }

  private static Map<Value, Integer> setValues(Map<Value, Integer> stats, Integer damage,
      Integer health, Integer armor) {
    stats.put(Value.DAMAGE, damage);
    stats.put(Value.HEALTH, health);
    stats.put(Value.ARMOR, armor);
    return stats;
  }

  private static Integer parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  enum Value {
    HEALTH(250),
    DAMAGE(45),
    ARMOR(10);

    private int value;

    Value(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Radostin Dimkov on 05.08.19
 */
class DragonArmy {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int linesCount = Integer.parseInt(reader.readLine());

    Map<String, Map<String, Map<Value, Integer>>> types = new LinkedHashMap<>();
    while (linesCount-- > 0) {
      String[] input = reader.readLine().split("\\s+");
      String type = input[0];
      String name = input[1];
      Integer damage = parseInt(input[2]) == null ? Value.DAMAGE.getValue() : parseInt(input[2]);
      Integer health = parseInt(input[3]) == null ? Value.HEALTH.getValue() : parseInt(input[3]);
      Integer armor = parseInt(input[4]) == null ? Value.ARMOR.getValue() : parseInt(input[4]);

      if (!types.containsKey(type)) {
        types.put(type, new TreeMap<>());
      }

      //should override if existing dragon with new stats
      types.get(type).put(name, setValues(
          types.get(type).get(name) == null ? new LinkedHashMap<>()
              : types.get(type).get(name), damage, health, armor));
    }

    for (Entry<String, Map<String, Map<Value, Integer>>> entrySet : types.entrySet()) {
      double avgDamage = getAverage(entrySet.getValue(), Value.DAMAGE);
      double avgHealth = getAverage(entrySet.getValue(), Value.HEALTH);
      double avgArmor = getAverage(entrySet.getValue(), Value.ARMOR);

      String type = entrySet.getKey();
      System.out.println(
          String.format("%s::(%.2f/%.2f/%.2f)", entrySet.getKey(), avgDamage, avgHealth,
              avgArmor)
      );

      entrySet.getValue().forEach((dragon, stats) -> {
        System.out.println(String.format("-%s -> damage: %d, health: %d, armor: %d", dragon,
            stats.get(Value.DAMAGE), stats.get(Value.HEALTH), stats.get(Value.ARMOR)));
      });
    }
  }

  private static double getAverage(Map<String, Map<Value, Integer>> dragons, Value type) {
    int sum = 0;
    double i = 0;
    for (Entry<String, Map<Value, Integer>> entry : dragons.entrySet()) {
      sum += entry.getValue().get(type);
      i++;
    }
    return sum / i;
  }

  private static Map<Value, Integer> setValues(Map<Value, Integer> stats, Integer damage,
      Integer health, Integer armor) {
    stats.put(Value.DAMAGE, damage);
    stats.put(Value.HEALTH, health);
    stats.put(Value.ARMOR, armor);
    return stats;
  }

  private static Integer parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  enum Value {
    HEALTH(250),
    DAMAGE(45),
    ARMOR(10);

    private int value;

    Value(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}