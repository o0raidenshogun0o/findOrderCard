package logic;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    List<List<String>> generatePermutations(List<String> arr) {
        List<List<String>> result = new ArrayList<>();
        generatePermutations(arr, new ArrayList<>(), new boolean[arr.size()], result);
        return result;
    }

    void generatePermutations(List<String> arr, List<String> temp, boolean[] used, List<List<String>> result) {
        if (temp.size() == arr.size()) {
            result.add(List.copyOf(temp));
            return;
        }
        for (int i = 0; i <= arr.size() - 1; i++) {
            if (used[i]) {
                continue;
            }
            temp.add(arr.get(i));
            used[i] = true;
            generatePermutations(arr, temp, used, result);
            temp.removeLast();
            used[i] = false;
        }
    }
}
