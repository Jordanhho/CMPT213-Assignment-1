package ca.cmpt213.as1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jordan on 19/01/2017.
 */


/**
 * FileFunctions Class is supposed to store the methods to filter the File for files matching extensions, return a list of the files, and write to file methods, obtain filelist from reading a source.txt
 */
public class FileFunctions
{
    /**
     * Method to search through a directory recursively to find all files or files with certain extensions and returns it in an ArrayList fileList
     * @param sourceFolder the sourceFolder where we are listing all the files from
     * @param input user input arguments
     * @param fileList the List of files that we are storing all these file locations t
     */
    public static void listAllFilesExt(String sourceFolder, String[] input, ArrayList<File> fileList)
    {
        try
        {
            if(input.length > 2)
            {
                File Folder = new File(sourceFolder);
                File[] fileArray = Folder.listFiles();

                for(int i = 2; i < input.length; i++)
                {
                    AnyExtFilter filter = new AnyExtFilter(input[i]);
                    File source = new File(sourceFolder);
                    File[] filterList = source.listFiles(filter);
                    for (File file : filterList)
                    {
                        fileList.add(file);
                    }
                }

                //Recursively search through all files and folders
                for (File files : fileArray)
                {
                    if (files.isDirectory())
                    {
                        listAllFilesExt(files.getAbsolutePath(), input, fileList);
                    }
                }
            }
            //Returns all files when no extension argument is given
            else
            {
                File Folder = new File(sourceFolder);
                File[] fileArray = Folder.listFiles();

                for (File file : fileArray)
                {
                    if (file.isFile())
                    {
                        fileList.add(file);
                    }
                    else if (file.isDirectory())
                    {
                        listAllFilesExt(file.getAbsolutePath(), input, fileList);
                    }
                }
            }
        }
        catch (Exception null_pointer)
        {
            System.out.println("Error, null pointer on file location");
            System.exit(0);
        }
    }


    /**
     * Method to list all Files
     * @param sourceFolder the sourceFolder where we are listing all the files from
     * @param fileList the List of files that we are storing all these file locations to
     */
    public static void listAllFiles(String sourceFolder, ArrayList<File> fileList)
    {
        System.out.println("Source file is: " + sourceFolder);
        try
        {
            File Folder = new File(sourceFolder);
            File[] fileArray = Folder.listFiles();
            if(fileArray.length > 0 && fileArray != null)
            {
                for (File file : fileArray)
                {
                    if (file.isFile())
                    {
                        fileList.add(file);
                    }
                    else if (file.isDirectory())
                    {
                        listAllFiles(file.getAbsolutePath(), fileList);
                    }
                }
            }
        System.out.println("Error, null pointer on file location");
        System.exit(0);

        }
        catch (Exception null_pointer)
        {
            System.out.println("Error, null pointer on file location");
            System.exit(0);
        }
    }


    /**
     * Method to write to a list of file locations to a file
     * @param input user input of ar guments
     * @param fileList the fileList of array of files
     */
    public static void writeFileList(String[] input, List<File> fileList)
    {
        System.out.println("\nWriting file list to output file: " + input[1]);
        File targetFile = new File(input[1]);
        try
        {
            PrintWriter writer = new PrintWriter (targetFile);
            for(File write: fileList)
            {
                writer.println(write);
            }
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Method to obtain all the files from the source file.txt that are valid and sorts from smallest to largest
     * @param fileList given a list of type FileList to store File location and size
     * @param sourceFile a file source.txt file to search for the files
     * @return warningList which is basicaly an arrayList of all the flagged non-existing files from the source.txt
     */
    public static List<String> getSourceFiles(ArrayList<FileListType> fileList, String sourceFile)
    {
        File file = new File(sourceFile);
        List<String> warningFiles = new ArrayList<>();
        try
        {
            System.out.print("\n");

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {

                String text = scanner.nextLine();

                if(!ValidInput.fileExists(text))
                {
                    warningFiles.add("Warning: File does not exist " + text);
                }
                else
                {
                    File textFile = new File(text);
                    fileList.add(new FileListType(textFile, textFile.length()));
                }
            }
            java.util.Collections.sort(fileList);
            java.util.Collections.reverse(fileList);
            return warningFiles;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no such sourceFile.txt is found");
            System.exit(0);
            return warningFiles;
        }
    }
}
