public enum FileTypes {
    MD(".md"),
    TXT(".txt");

    private String fileString;

    FileTypes (String fileString) {
        this.fileString = fileString;
    }

    public String getFileString() {
        return fileString;
    }
}
