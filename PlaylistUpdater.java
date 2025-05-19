import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistUpdater {
    public static void main(String[] args) throws IOException {
        //setting for different file extensions
        final FileTypes FILETYPE = FileTypes.MD;
        //delimiter string used for naming playlists to maintain links
        String delimString = "- #";
        //setting which determines where to store created playlists
        int storeInside = 0;

        //ArrayList for playlists
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        PrintWriter[] writers = new PrintWriter[2];

        //grab path for files to be exposed to parent directory
        Path relativePath = Paths.get("");
        Path playListGeneratorPath = relativePath.toAbsolutePath();
        Path parentPath = playListGeneratorPath.getParent();
        
        //set filewriters for generated playlists
        writers[1] = new PrintWriter(new BufferedWriter(new FileWriter("generatedPlaylistsUntitled"  + FILETYPE.getFileString())));
        
        //set scanner input to the source file
        Scanner scan = new Scanner(new File(parentPath + "/playlistsSources" + FILETYPE.getFileString()));

        //switch for storing inside subfolers  or not
        Path tempPath;
        switch (storeInside) {
            case 0:
                tempPath = Paths.get(parentPath + "/");
                break;
            default:
                tempPath = Paths.get(playListGeneratorPath + "/playlists" + FILETYPE.getFileString() + "/");
                break;
        }
        
        //set output for generation
        writers[0] = new PrintWriter(new BufferedWriter(new FileWriter(tempPath + "/generatedPlaylistsTitled" + FILETYPE.getFileString())));

        //add each playlist to the playlist array, sourcing from scanner, initializing with full path
        while (scan.hasNext()) {
            String filename;
            switch (FILETYPE) {
                case TXT:
                    filename = scan.next();
                    playlists.add(new Playlist(tempPath + filename + FILETYPE.getFileString(), tempPath + "", filename));
                    break;
                case MD:
                    scan.useDelimiter(delimString);
                    
                default:
                    break;
            } 
            filename = scan.next();
            playlists.add(new Playlist(tempPath + "/" + filename.trim() + FILETYPE.getFileString(), tempPath + "", filename.trim()));
        }

        //create new file if it doesn't exist
        for (Playlist playlist : playlists) {
            File currentFile = new File (playlist.getPathString());
            try {
                if(currentFile.createNewFile()) {
                    System.out.println("Created new file: " + playlist.getPathString());
            }
            } catch (Exception e) {
            }
        }

        //sysout all playlist links and print to file
        for (Playlist playlist : playlists) {
            String printString = "";
            switch (FILETYPE) {
                case MD:
                    printString = delimString + playlist.getFileString() + ": ";
                    break;
                case TXT:

                    break;
                default:
                    break;
            }
            
            String[] argsForGenerator = {playlist.getPathString()};

            for (int i = 0; i < writers.length; i++) {

                if(i == 0) {
                    writers[i].print(printString);
                    System.out.print(printString);
                    System.out.println(FileImporter.importFile(PlaylistGeneratorMain.getFileName(argsForGenerator)));
                }
                    writers[i].println(FileImporter.importFile(PlaylistGeneratorMain.getFileName(argsForGenerator)));
            }

        }

        //close all writers and scanners
        for (PrintWriter writer : writers) {
            writer.close();
        }
        scan.close();
    }
}
