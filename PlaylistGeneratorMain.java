import java.util.Scanner;

public class PlaylistGeneratorMain {
    public static void main(String[] args) {
        
        //read in file, output string of link to playlist
        System.out.println(FileImporter.importFile(getFileName(args)));

    }
    
    public static String getFileName(String[] args) {
        //create scanner for user input
        Scanner scan = new Scanner(System.in);

        //create string for read
        String fileName;

        //check if cmd line args were passed, use default case if 1
        if(args.length == 1) {
            if(args[0] == "1") {
                fileName = "links.txt";
            } else {
                fileName = args[0];
            }
        } else {
            //prompt filename input
            System.out.print("Please input filename to read, or input '1' to use default filename links.txt: ");
            fileName = scan.next();

            if ((fileName.charAt(0) == '1' && fileName.length() == 1) || fileName.length() == 0) {
                fileName = "links.txt";
                System.out.println("used default");
            }
        }

        scan.close();
        return fileName;

    }

}
