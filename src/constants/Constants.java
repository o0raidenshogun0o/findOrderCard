package constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Constants {
    private static final String TEMPLATE_1 = "%s được dùng lần thứ %d tại card %d";
    public static final String TEMPLATE_2 = "Có tất cả: %d cách";

    public static final String IBUKI = optimizeString("Ibuki");
    public static final int MAX = 50;

    public static String optimizeString(String name) {
        return name.toLowerCase(Locale.ROOT).trim();
    }

//    public static String getTemplate(String useCard, int order, int index) {
//        return Constants.TEMPLATE.formatted(useCard, order, index);
//    }

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

    public static boolean checkIbuki(List<String> startSkill, int length) {
        for (int i = 0; i <= length - 1; i++) {
            if (startSkill.get(i).equals(IBUKI)) {
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
