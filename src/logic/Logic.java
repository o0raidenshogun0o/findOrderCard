package logic;

import constants.Constants;

import java.util.*;

public class Logic {
    String[] currentCards;
    ArrayDeque<String> holdCards;
    Map<String, int[]> mapNotification;

    private  List<String> START_SKILL;
    private Boolean CHECK_IS_VALID = true;
    private Boolean IS_HAVE_IBUKI_SWIMSUIT = false;
    private final int SPLIT;

    public Logic(List<String> startSkill , List<String> tlSkill) {
        SPLIT = startSkill.size() / 2;
        for (String name : startSkill) {
            if (name.equals(Constants.IBUKI_SWIMSUIT)) {
                IS_HAVE_IBUKI_SWIMSUIT = true;
                break;
            }
        }
        if (IS_HAVE_IBUKI_SWIMSUIT && !Constants.checkIbukiSwimsuit(startSkill, SPLIT)) {
            CHECK_IS_VALID = false;
            return;
        }

        START_SKILL = List.copyOf(startSkill);
        currentCards = new String[SPLIT];
        holdCards = new ArrayDeque<>();
        mapNotification = new HashMap<>();

        for (int i = 0; i <= currentCards.length - 1; i++) {
            currentCards[i] = START_SKILL.get(i);
        }
        for (int i = currentCards.length; i <= START_SKILL.size() - 1; i++) {
            holdCards.addLast(START_SKILL.get(i));
        }

        for (String name : START_SKILL) {
            mapNotification.put(name, new int[Constants.MAX]);
            Arrays.fill(mapNotification.get(name), -1);
            mapNotification.get(name)[0] = 0; //mapCount

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
