package logic;//import constants.Constants;
//
//import java.util.*;
//
//
//public class logic.Solution {
//    String[] currentCards = new String[3];
//    ArrayDeque<String> holdCards = new ArrayDeque<>();
//
//    Map<String, Integer> mapCount = new HashMap<>();
//    Map<String, List<String>> mapNotification = new HashMap<>();
//
//    final List<String> START_SKILL;
//    Boolean CHECK_IS_VALID = true;
//    Boolean IS_HAVE_IBUKI = false;
//
//    public logic.Solution(List<String> startSkill, List<String> tlSkill) {
//        START_SKILL = List.copyOf(startSkill);
//
//        for (int i = 0; i <= currentCards.length - 1; i++) {
//            currentCards[i] = startSkill.get(i);
//        }
//        for (int i = currentCards.length; i <= startSkill.size() - 1; i++) {
//            holdCards.addLast(startSkill.get(i));
//        }
//
//        for (String name : startSkill) {
//            if (startSkill.equals(Constants.IBUKI)) {
//                IS_HAVE_IBUKI = true;
//            }
//            mapNotification.put(name, new ArrayList<>());
//        }
//
//        update(tlSkill);
//    }
//
//    void update(List<String> tlSkill) {
//        for (String useCard : tlSkill) {
//            update(useCard);
//        }
//    }
//
//    void update(String _useCard) {
//        String useCard = Constants.optimizeString(_useCard);
//
//        int index = -1;
//        int tempCount = 0;
//        for (int i = 0; i <= currentCards.length - 1; i++) {
//            if (useCard.equals(currentCards[i])) {
//                index = i;
//                tempCount++;
//            }
//        }
//        if (index == -1 || tempCount != 1) {
//            CHECK_IS_VALID = false;
//            return;
//        }
//
//        String nextCard = holdCards.removeFirst();
//        currentCards[index] = nextCard;
//        holdCards.addLast(useCard);
//
//        mapCount.put(useCard, mapCount.getOrDefault(useCard, 0) + 1);
//        int realIndex = index + 1;
//        mapNotification.get(useCard).add(Constants.getTemplate(useCard, mapCount.get(useCard), realIndex));
//    }
//
//    List<String> getNotification(String card) {
//        return mapNotification.get(Constants.optimizeString(card));
//    }
//}


import constants.Constants;

import java.util.*;


public class Logic {
    String[] currentCards;
    ArrayDeque<String> holdCards = new ArrayDeque<>();

    Map<String, int[]> mapNotification = new HashMap<>();

    private final List<String> START_SKILL;
    private Boolean CHECK_IS_VALID = true;
    private Boolean IS_HAVE_IBUKI = false;

    public Logic(List<String> startSkill, List<String> tlSkill) {
        START_SKILL = List.copyOf(startSkill);

        {
            int SPLIT = START_SKILL.size() / 2;
            currentCards = new String[SPLIT];
        }

        for (int i = 0; i <= currentCards.length - 1; i++) {
            currentCards[i] = START_SKILL.get(i);
        }
        for (int i = currentCards.length; i <= START_SKILL.size() - 1; i++) {
            holdCards.addLast(START_SKILL.get(i));
        }

        for (String name : START_SKILL) {
            if (name.equals(Constants.IBUKI)) {
                IS_HAVE_IBUKI = true;
            }
            mapNotification.put(name, new int[Constants.MAX]);
            Arrays.fill(mapNotification.get(name), -1);
            mapNotification.get(name)[0] = 0; //mapCount

        }

        if (IS_HAVE_IBUKI && !Constants.checkIbuki(START_SKILL, currentCards.length)) {
            CHECK_IS_VALID = false;
            return;
        }
        update(tlSkill);
    }

    public int[] getMapNotification(String name) {
        return mapNotification.get(Constants.optimizeString(name));
    }

    private void update(List<String> useCards) {
        for (String useCard : useCards) {
            update(useCard);
        }
    }

    private void update(String _useCard) {
        String useCard = Constants.optimizeString(_useCard);

        int index = -1;
        int tempCount = 0;
        for (int i = 0; i <= currentCards.length - 1; i++) {
            if (useCard.equals(currentCards[i])) {
                index = i;
                tempCount++;
            }
        }
        if (index == -1 || tempCount != 1) {
            CHECK_IS_VALID = false;
            return;
        }

        String nextCard = holdCards.removeFirst();
        currentCards[index] = nextCard;
        holdCards.addLast(useCard);

        int[] arrayNotificationOfUseCard = mapNotification.get(useCard);
        arrayNotificationOfUseCard[0]++;
        int realIndex = index + 1;
        arrayNotificationOfUseCard[arrayNotificationOfUseCard[0]] = realIndex;
    }

    public boolean isValid() {
        return CHECK_IS_VALID;
    }

    public List<String> getStartSkill() {
        return START_SKILL;
    }
}
