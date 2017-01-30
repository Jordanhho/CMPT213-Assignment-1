package ca.cmpt213.as1;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nova on 25/01/2017.
 */

/**
 * DisplayInfo class is supposed to store methods to display the interface that can be used by both FileLister and FileCollector
 */
public class DisplayInfo
{
    /**
     * Method to display a menu title with stars below it
     * @param title a user input title name
     */
    public static void displayTitle(String title)
    {
        System.out.println("\n" + title);
        for(int i = 0; i < title.length() + 5; i++)
        {
            System.out.print("*");
        }
        System.out.print("\n");
    }


    /**
     * Method to Display FileLister or FileCollector menu with data that calls upon other methods to filter files matching extensions or list all files
     * @param Menu the Menu Array to display
     * @param input the user input arguments
     * @param Title the title of the menu the user wants to display
     * @param program the program which will either be "FileLister" or "FileCollector" that will be used to identify which program uses which
     */
    public static void displayMenu(String[] Menu, String[] input, String Title, String program)
    {
        displayTitle(Title);
        if(program.equals("FileLister"))
        {
            String sourceFolder = input[0];
            ArrayList<File> files = new ArrayList<>();
            FileFunctions.listAllFilesExt(sourceFolder, input, files);
            int j = 0;
            double totalFolderSize = 0.00;


            //obtain the total size of all files combined
            for (File file : files)
            {
                totalFolderSize = totalFolderSize + file.length();
            }
            for (int i = 0; i < Menu.length - 1; i++)
            {
                if (i < 2)
                {
                    System.out.printf("%12s  %s\n", Menu[i], input[j]);
                    j++;
                }
                else if (i == 2)
                {
                    System.out.printf("%12s  ", Menu[i]);
                    while (j < input.length)
                    {
                        System.out.print(input[j] + " ");
                        j++;
                    }
                    if (input.length <= 2)
                    {
                        System.out.print("No Extensions Found");
                    }
                    System.out.print("\n");
                }
                else
                {
                    System.out.printf("%12s  %d\n", Menu[i], files.size());
                    System.out.printf("%12s  %,.2f MiB (%,.0f bytes)\n", Menu[i + 1], totalFolderSize / (double) 1024 / 1024, totalFolderSize);
                }
            }
        }
        else if(program.equals("FileCollector"))
        {
            for (int i = 0; i < Menu.length; i++)
            {
                System.out.printf("%28s  %s\n", Menu[i], input[i]);
            }
        }
    }
}
