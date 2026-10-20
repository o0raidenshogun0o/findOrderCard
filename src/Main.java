import constants.Constants;
import logic.Solution;

void main() throws IOException {
    String path = Constants.NAME_FILE;
    Scanner sc = new Scanner(new File(getFullPath("conditions", path)));
    Map<String, int[]> conditions = new HashMap<>();
    int n = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i <= n - 1; i++) {
        String name;
        int count;
        {
            String[] parts = Constants.optimizeString(sc.nextLine()).split("=", 2);
            name = Constants.optimizeString(parts[0]);
            count = Integer.parseInt(Constants.optimizeString(parts[1]));
        }
        int[] tempCondition = new int[Constants.MAX];
        Arrays.fill(tempCondition, -1);
        for (int j = 0; j < count; j++) {
            String[] numbers = Constants.optimizeString(sc.nextLine()).split(Constants.WHITESPACE_REGEX);
            int a = Integer.parseInt(Constants.optimizeString(numbers[0]));
            int b = Integer.parseInt(Constants.optimizeString(numbers[1]));
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