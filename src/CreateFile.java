import constants.Constants;

void main() throws IOException {
    String name = Constants.NAME_FILE;
    createFiles(name);
}

private void createFiles(String name) throws IOException {
    createFile(name, "conditions");
    createFile(name, "cards");
    createFile(name, "timelines");

}


private void createFile(String name, String path) throws IOException {
    String preName = path.substring(0, path.length() - 1);
    Path createPath = Path.of(path, "%s%s.txt".formatted(preName, name));
    Files.createDirectories(createPath.getParent());
    if (Files.notExists(createPath)) {
        Files.createFile(createPath);
    }
}