package ca.cmpt213.as1;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jordan on 19/01/2017.
 */


/**
 * FilerLister class is supposed to:
 * accept user input of source directory, target file directory and optional extension arguments
 * returns a list of files with the matched extensions or returns all files if not extensions are entered
 * error checking if inputs are valid such as if a directory exists and valid extensions
 */
public class FileLister
{
    /**
     * Method to Displays the file information for FileLister
     * @param input user input arguments, arg[0] is sourceFolder location, arg[1] target File location, arg[2] and beyond are the file extensions which starts with a .
     */
    public void display(String[] input)
    {
        //Gives out hints when typed just the class
        ValidInput.userValidation(input, "FileLister");

        String sourceFolder = input[0];
        ArrayList<File> files = new ArrayList<>();
        FileFunctions.listAllFilesExt(sourceFolder, input, files);

        String[] Menu = {"Source Path:", "Target Path:", "Extensions: ", "Files Found:", "Total Size: "};
        DisplayInfo.displayMenu(Menu, input, "Statistics on Files found", "FileLister");

        //Lists out files found
        DisplayInfo.displayTitle("Files Found: ");
        if(files.size() == 0)
        {
            System.out.println("No Files Found, No need to write empty list to file");
        }
        else
        {
            for (File file : files)
            {
                System.out.println(" File: " + file.getAbsolutePath());
            }

            //Writes the list of files contained in files to target file
            FileFunctions.writeFileList(input, files);
        }
    }

    /**
     * Method tester for program FileLister
     * @param args user input arguments
     */
    public static void main(String[] args)
    {
        FileLister fileLister = new FileLister();
        fileLister.display(args);
    }
}
