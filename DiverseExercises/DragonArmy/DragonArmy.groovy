/**
 * @author Radostin Dimkov on 05.08.19
 */
class DragonArmy {

    static void main(String[] args) {
        BufferedReader reader = System.in.newReader()
        int linesCount = Integer.parseInt(reader.readLine())

        Map<String, Map<String, Map<Value, Integer>>> types = new LinkedHashMap<>()
        while (linesCount-- > 0) {
            String[] input = reader.readLine().split("\\s+")
            def (type, name, damage, health, armor) =
                [input[0],
                 input[1],
                 input[2] == "null" ? Value.DAMAGE.getValue() : input[2] as Integer,
                 input[3] == "null" ? Value.HEALTH.getValue() : input[3] as Integer,
                 input[4] == "null" ? Value.ARMOR.getValue() : input[4] as Integer]

            if(!types.containsKey(type)){
                types[type] = [:]
            }
            //should override if existing dragon with new stats
            types[type] << [(name): setValues(types?.(type)?.(name) ?: [:], damage, health, armor)]
        }

        types.each {entrySet ->
            def (avgDamage, avgHealth, avgArmor) =
                [getAverage(entrySet.getValue(), Value.DAMAGE), getAverage(entrySet.getValue(), Value.HEALTH), getAverage(entrySet.getValue(), Value.ARMOR)]
            println "${entrySet.key}::" + String.format("(%.2f/%.2f/%.2f)",avgDamage, avgHealth, avgArmor)

            entrySet.value.sort({dragon -> dragon.key}).each { dragon ->
                println "-${dragon.key} -> damage: ${dragon.value[Value.DAMAGE]}, health: ${dragon.value[Value.HEALTH]}, armor: ${dragon.value[Value.ARMOR]}"
            }
        }
    }

    private static double getAverage(Map<String, Map<Value, Integer>> dragons, Value type) {
        int sum = 0
        double i = 0
        dragons.each {entry ->
            sum += entry.value[type]
            i++
        }
        return sum / i
    }

    private static Map<Value, Integer> setValues(Map<Value, Integer> stats, Integer damage,
                                                 Integer health, Integer armor) {
        stats[Value.DAMAGE] = damage
        stats[Value.HEALTH] = health
        stats[Value.ARMOR] = armor
        return stats
    }

    enum Value {
        HEALTH(250),
        DAMAGE(45),
        ARMOR(10)

        private int value

        Value(int value) {
            this.value = value
        }

        int getValue() {
            return value
        }
    }
}