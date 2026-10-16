package constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Constants {
    public static final String WHITESPACE_REGEX = "\\s+";
    public static final String TEMPLATE_2 = "Có tất cả: %d cách";

    public static final String IBUKI_SWIMSUIT = optimizeString("Ibuki (Swimsuit)");
    public static final int MAX = 50;

    public static String optimizeString(String name) {
        return name.toLowerCase(Locale.ROOT).trim();
    }

    public static List<String> readFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String part : line.split("->")) {
                    String word = part.toLowerCase(Locale.ROOT).trim();
                    result.add(word);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIbukiSwimsuit(List<String> startSkill, int length) {
        for (int i = 0; i <= length - 1; i++) {
            if (startSkill.get(i).equals(IBUKI_SWIMSUIT)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkConditionArray(int[] conditions, int[] list) {
        // do list[0] la mapCount nen i chay tu 1
        for (int i = 1; i <= Constants.MAX - 1; i++) {
            if (conditions[i] != -1) {
                if (conditions[i] != list[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
