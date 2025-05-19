import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileImporter {
    public static String importFile (String fileName) {
        //make playlistlink string, and arraylist for imported links
        String playlistLink = "";
        ArrayList<String> importedLinks = new ArrayList<String>();

        //read in links
        importedLinks = FileImporter.importLinks(fileName);

        //concatenate
        playlistLink = FileImporter.makePlaylist(importedLinks);
        return playlistLink;
    }

    //import arraylist of links
    private static ArrayList<String> importLinks (String inputFileName) {
        // String delimiterString;
        String tempString = "";
        ArrayList<String> linksToReturn = new ArrayList<String>();
        
        //make input file
               File inputFile = new File(inputFileName);
        //create scanner and setup to use delimiter
        try (Scanner scan = new Scanner(inputFile)) {
            //check for next line/link and read in 
            while (scan.hasNext()) {
                //read next link
                tempString = scan.next();
               // System.out.println(tempString);
                linksToReturn.add(tempString);
            }
            //close scanner
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        //return arraylist of links
        return linksToReturn;                
    }
    //format arraylist and return as string
    private static String makePlaylist (ArrayList<String> links) {
        String playlistString = "";
        ArrayList<String> vidIDs = new ArrayList<>();
        String tempString = "";
        for (String s : links) {
            tempString = s.replaceAll("https://www\\.youtube\\.com/watch\\?v=|www\\.youtube\\.com/watch\\?v=", "");
            vidIDs.add(tempString);
        }

        playlistString += "http://www.youtube.com/watch_videos?video_ids=";
        for (String id : vidIDs) {
            playlistString += id + ",";
        }

        //remove last comma
        playlistString = playlistString.substring(0, playlistString.length() - 1);
        
        return playlistString;
    }
}
