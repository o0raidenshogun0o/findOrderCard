import constants.Constants;
import logic.Solution;

void main() throws IOException {
    String path = "[Fuyu, Iroha, Satsuki, Hoshino, Kisaki, Nagisa]";
    Scanner sc = new Scanner(new File(getFullPath("conditions", path)));
    Map<String, int[]> conditions = new HashMap<>();
    int n = sc.nextInt();
    for (int i = 0; i < n; i++) {
        String name = sc.next();
        int count = sc.nextInt();
        int[] tempCondition = new int[Constants.MAX];
        Arrays.fill(tempCondition, -1);
        for (int j = 0; j < count; j++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            tempCondition[a] = b;
        }

        conditions.put(name, tempCondition);
    }


    List<String> cards = Constants.readFile(getFullPath("cards", path));
    List<String> tlSkill = Constants.readFile(getFullPath("timelines", path));
    Solution solution = new Solution(cards, tlSkill);
    List<List<String>> startSkill = solution.solve(conditions);
    System.out.println(Constants.TEMPLATE_2.formatted(startSkill.size()));
    for (List<String> order : startSkill) {
        System.out.println(order);
    }
}


private String getFullPath(String type, String path) {
    String getRealType = switch (type) {
        case "cards" -> "cards/card";
        case "timelines" -> "timelines/timeline";
        case "conditions" -> "conditions/condition";
        default -> null;
    };
    return "%s%s.txt".formatted(getRealType, path);
}