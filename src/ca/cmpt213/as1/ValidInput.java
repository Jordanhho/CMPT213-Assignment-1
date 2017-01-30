package ca.cmpt213.as1;

import java.io.File;

/**
 * Created by Jordan on 19/01/2017.
 */

/**
 * ValidInput class is used to verify the user's input that will be used by the programs FileCollector and FileLister
 * Verifies correct number of arguments, if valid extension, if folder or file exists, if it is a number
 */
public class ValidInput
{
    private static final String[] VALID_EXTENSIONS = new String[] {".txt", ".mp3", ".wma", ".zip", ".html", ".jpg", ".wav", ".mp4", ".mod"};

    /**
     * Method to validate the user's input based on which program it is running for
     * @param input user input arguments
     * @param program the program this corresponds to either "FileLister" or "FileCollector"
     */
    public static void userValidation(String[] input, String program)
    {
        if(program.equals("FileLister"))
        {
            helper(input, "FileLister");
            if(!minArgument(input.length, 2, "FileLister"))
            {
                System.out.println("Try the following command: java ca.cmpt213.as1.FileLister <source folder> <target file> <Extensions(starts with .)>");
                System.exit(0);
            }

            if(!validSourceFolder(input[0]) || !validFile(input[1]) || !validExtensions(input))
            {
                System.out.println("Try the following command: java ca.cmpt213.as1.FileLister <source folder> <target file> <Extensions(starts with .)>");
                System.exit(0);
            }
        }
        else if(program.equals("FileCollector"))
        {
            helper(input, "FileCollector");
            if(!minArgument(input.length, 3, "FileCollector"))
            {
                System.out.println("Try the following command: java ca.cmpt213.as1.FileCollector <Number of Collections> <Collection size> <source file>\n");
                System.exit(0);
            }

            if (!validNumOfCollections(input[0]) || !validCollectionSize(input[1]) || !validFile(input[2]))
            {
                System.out.println("Try the following command: java ca.cmpt213.as1.FileCollector <Number of Collections> <Collection size> <source file>\n");
                System.exit(0);
            }

        }
    }


    /**
     * Method to verify number of input arguments
     * @param inputLen user input length of argument
     * @param min the minimum required input arguments
     * @param program the program this method will correspond to
     * @return a boolean value if the minimum argument is reached true, else false
     */
    private static boolean minArgument(int inputLen, int min, String program)
    {
        //Checks if the amount of arguments reaches the minimum amount
        if(program.equals("FileCollector") && inputLen > 3)
        {
            System.out.println("Error, the Number of Arguments over exceeds the required amount, Maximum number of input Arguments should be 3");
            return false;
        }

        else if (inputLen < min && inputLen > 0)
        {
            System.out.println("Error, the Number of Arguments does not match the required amount, Minimum number of input Arguments should be " + min);
            return false;
        }
        return true;
    }


    /**
     * Method to verify if Source file is valid
     * @param sourceFolder the source Folder location
     * @return a boolean value if it is valid true, else false
     */
    private static boolean validSourceFolder(String sourceFolder)
    {
        File file = new File(sourceFolder);
        //Checks if the fold the source is contained in exists
        if(!file.isDirectory())
        {
            System.out.println("Error, the entered source folder does not exist");
            return false;
        }
        return true;
    }


    /**
     * Method to verify if target File is valid
     * @param filePath the file location path
     * @return a boolean if file is valid true, else false
     */
    public static boolean validFile(String filePath)
    {
        File file = new File(filePath);
        try
        {
            //Checks if targetfile is a directory or not
            if(file.isDirectory())
            {
                System.out.println("Error, the entered file is a directory");
                return false;
            }
            //Checks if the folder we are writing to exists or not
            file = file.getParentFile();
            if(!file.exists())
            {
                System.out.println("Error, the entered file folder does not exist");
                return false;
            }
            int i = 0;
            boolean Found = false;
            while(i < VALID_EXTENSIONS.length && !Found)
            {
                if(filePath.toLowerCase().endsWith(VALID_EXTENSIONS[i]))
                {
                    Found = true;
                }
                i++;
            }
            if(!Found)
            {
                System.out.println("Error, the entered file extension or syntax for the file is invalid");
                return false;
            }
            return true;
        }
        catch (Exception null_pointer)
        {
            System.out.println("Error, the syntax for the file is invalid");
            return false;
        }
    }


    /**
     * Method to check if file exists
     * @param sourceFile the source File location
     * @return a boolean value if file exists true, else false
     */
    public static boolean fileExists(String sourceFile)
    {
        File file = new File(sourceFile);
        if(!file.exists())
        {
            return false;
        }
        return true;
    }


    /**
     * Method to verify if argument Extensions are valid
     * @param input user input arguments
     * @return a boolean value if extension is valid or not based on VALID EXTENSIONS array of correct extensions
     */
    private static boolean validExtensions(String[] input)
    {
        //Checks if the entered extensions are valid
        for(int j = 2; j < input.length; j++)
        {
            boolean Found = false;
            int i = 0;
            while (i < VALID_EXTENSIONS.length && !Found)
            {
                if (input[j].toLowerCase().endsWith(VALID_EXTENSIONS[i]))
                {
                    Found = true;
                }
                i++;
            }
            if (!Found)
            {
                System.out.println("Error, the entered filter file extension is invalid");
                return false;
            }
        }
        return true;
    }


    /**
     * Method to Prints out the class input argument command for help
     * @param input user input argument
     * @param program the program this corresponds to either "FileLister" or "FileCollector"
     */
    private static void helper(String[] input, String program)
    {
        if(input.length == 0)
        {
            if(program.equals("FileLister"))
            {
                System.out.println("Usage");
                System.out.println(" java FileLister <source folder> <target file> <filters>\n");
                System.out.println("Examples:");
                System.out.println(" == Return all files without extensions: ==");
                System.out.println(" java ca.cmpt213.as1.FileLister C:\\Music\\ C:\\list.txt");
                System.out.println(" == Return all files with matched extension(s): ==");
                System.out.println(" java ca.cmpt213.as1.FileLister C:\\Music\\ C:\\list.txt .mp3");
                System.out.println(" java ca.cmpt213.as1.FileLister C:\\ C:\\test\\files.txt .mp3 .jpg\n");
                System.exit(0);
            }

            else if(program.equals("FileCollector"))
            {
                System.out.println("Usage");
                System.out.println(" java ca.cmpt213.as1.FileCollector <# collections> <bytes per collection> <input list file>\n");
                System.out.println("Examples:");
                System.out.println(" java ca.cmpt213.as1.FileCollector 3 1024 daFiles.txt");
                System.out.println(" java ca.cmpt213.as1.FileCollector 10 1048576 c:\\files\\input.txt");
                System.exit(0);
            }
        }
    }


    /**
     * checks if number collections input is a valid number and is within the correct range
     * @param testNumber the testing string Number in int
     * @return boolean value if successfully converted into an integer true, else false
     */
    public static boolean validNumOfCollections(String testNumber)
    {
        try
        {
            int CollectionNum = Integer.parseInt(testNumber);
            if(0 <= CollectionNum && CollectionNum <= 1000)
            {
                return true;
            }
            System.out.println("Error, the entered Number of Collections is not within the correct range, please enter a number between 0 and 1000");
            return false;
        }
        catch(NumberFormatException Fail)
        {
            System.out.println("Error, the entered Number of Collections is not a number or larger than an integer");
            return false;
        }
    }


    /**
     * Method to check if collection size is a valid number and is within the correct range
     * @param testSize the testing string size in Long
     * @return a boolean value if successfully converted into a Long true, else false
     */
    public static boolean validCollectionSize(String testSize)
    {
        try
        {
            long CollectionNum = Long.parseLong(testSize);
            if(0L <= CollectionNum && CollectionNum <= 1000000000000L)
            {
                return true;
            }
            System.out.println("Error, the entered Collection Size is not within the correct range, please enter a number between 0 and 1,000,000,000,000 (1 trillion)");
            return false;
        }
        catch (NumberFormatException Fail)
        {
            System.out.println("Error, the entered Collections Size is not a number or larger than a long");
            return false;
        }
    }
}
