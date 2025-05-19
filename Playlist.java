public class Playlist {
    private String directoryString, pathString, fileString;

    public Playlist (String pathString) {
        this(pathString, null, null);
    }

    public Playlist (String pathString, String directoryString, String fileString) {
        this.directoryString = directoryString;
        this.pathString = pathString;
        this.fileString = fileString;
    }

    public void setDirectoryString(String directoryString) {
        this.directoryString = directoryString;
    }

    public String getDirectoryString() {
        return this.directoryString;
    }

    public void setPathString(String pathString) {
        this.pathString = pathString;
    }

    public String getPathString() {
        return this.pathString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public String getFileString() {
        return this.fileString;
    }
}
