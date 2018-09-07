import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    private static Map<Character, Integer> letters = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\|");

        String capitalLetters = findCapitalLetters(input);
        for (int i = 0; i < capitalLetters.length(); i++) {
            letters.put(capitalLetters.charAt(i), 0);
        }

        findLettersLenght(input);

        for (Map.Entry<Character, Integer> currentLetter : letters.entrySet()) {
            String thirdPart = input[2];

            Pattern pattern = Pattern.compile(" \\b"+ currentLetter.getKey() +
                    "[\\w\\d\\!\\\"#\\$%&\\'\\(\\)*+\\'\\-\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\]\\\\\\^\\_\\`\\{\\|\\}\\~]{"
                    + currentLetter.getValue() + "}\\b");
            Matcher matcher = pattern.matcher(thirdPart);
            if (matcher.find()) {
                String word = matcher.group();
                word = word.substring(1);
                System.out.println(word);
            }
        }


    }

    private static String findCapitalLetters(String[] input) {
        String firstPart = input[0];

        Pattern pattern = Pattern.compile("([\\#\\$\\%\\*\\&][A-Z]+[\\#\\$\\%\\*\\&])");
        Matcher matcher = pattern.matcher(firstPart);

        while (matcher.find()) {
            String letters = matcher.group(1);
            char startSymbol = letters.charAt(0);
            char endSymbol = letters.charAt(letters.length() - 1);
            if (startSymbol == endSymbol) {
                return letters.substring(1, letters.length() - 1);
            }
        }
        return null;
    }

    private static void findLettersLenght(String[] input) {
        String secondPart = input[1];

        Pattern pattern = Pattern.compile("([0-9]+:[0-9]+)");
        Matcher matcher = pattern.matcher(secondPart);

        while (matcher.find()) {
            String[] matches = matcher.group(1).split(":");
            if (matches[1].length() > 1) {
                char letter = (char) Integer.parseInt(matches[0]);
                if (ifLetterExists(letter)) {
                    letters.replace(letter, Integer.parseInt(matches[1]));
                }
            }
        }
    }


    private static boolean ifLetterExists(char letter) {
        for (Character character : letters.keySet()) {
            if (character == letter)
                return true;
        }
        return false;
    }
}
