package logic;

import constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Logic[] logics;

    // Cho cac List startSkill kha thi vao 1 map roi xem co cai nao handle duoc nhieu ten ko
    private Map<List<String>, Integer> mapHandle;
    private List<List<String>> startSkillValid;
    private int numberOfConditions;

    public Solution(List<String> cards, List<String> tlSkill) {
        logics = getLogics(cards, tlSkill);
        mapHandle = new HashMap<>();
        startSkillValid = new ArrayList<>();
    }

    private void getStartSkillOfCondition(Map<String, int[]> conditions) {
        numberOfConditions = conditions.size();
        for (var entry : conditions.entrySet()) {
            getStartSkillOfCondition(entry.getKey(), entry.getValue());
        }
        for (var entry : mapHandle.entrySet()) {
            if (entry.getValue() == numberOfConditions) {
                startSkillValid.add(List.copyOf(entry.getKey()));
            }
        }
    }

    private void getStartSkillOfCondition(String name, int[] condition) {
        for (int i = 0; i <= logics.length - 1; i++) {
            if (logics[i] == null) {
                continue;
            }
            int[] temp = logics[i].getMapNotification(name);
            if (Constants.checkConditionArray(condition, temp)) {
                List<String> startSkill = logics[i].getStartSkill();
                mapHandle.put(startSkill, mapHandle.getOrDefault(startSkill, 0) + 1);
            }
        }
    }

    private Logic[] getLogics(List<String> cards, List<String> tlSkill) {
        List<List<String>> generatePermutations = new Permutations().generatePermutations(cards);
        Logic[] logics = new Logic[generatePermutations.size()];

        for (int i = 0; i <= logics.length - 1; i++) {
            List<String> startSkill = generatePermutations.get(i);
            logics[i] = new Logic(startSkill, tlSkill);
            if (!logics[i].isValid()) {
                logics[i] = null;
            }
        }
        return logics;
    }

    public List<List<String>> solve(Map<String, int[]> conditions) {
        getStartSkillOfCondition(conditions);
        return startSkillValid;
    }
}
