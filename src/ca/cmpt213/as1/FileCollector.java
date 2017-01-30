package ca.cmpt213.as1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Jordan on 19/01/2017.
 */

/**
 * FileCollection Class is supposed to search for files from a filelist.txt and put the valid files in random order into user input number of collections and size of each collection
 */
public class FileCollector
{
    /**
     * Displays the file information for FileCollector
     * @param input the user input argument , arg[0] is number of collection size, arg[1] is size per collection, arg[2] is source File location
     */
    public void display(String[] input)
    {
        //Validates user Input
        ValidInput.userValidation(input, "FileCollector");


        //Menu Details and display
        String[] Menu = {"Number of Collections:", "Size(bytes) per Collection:", "Source File List Location:"};
        DisplayInfo.displayMenu(Menu, input, "Now building collection:", "FileCollector");


        //Obtaining user input
        int numCollection = Integer.parseInt(input[0]);
        Long CollectionSize = Long.parseLong(input[1]);
        String sourceFile = input[2];


        //String file to print out warnings of files not existing and Obtaining Files list that is sorted from greatest size to smallest
        ArrayList<FileListType> fileList = new ArrayList<>();
        List<String> warningFiles = new ArrayList<>();
        warningFiles = FileFunctions.getSourceFiles(fileList, sourceFile);


        //Creating the Collection list
        List<Collection> CollectionList = new ArrayList<>();
        int i = 0;
        for(i = 0; i < numCollection; i++)
        {
            CollectionList.add(new Collection(CollectionSize));
        }


        //Anonymous class  for Extra Collection of unlimited size
        Collection ExtraCollection = new Collection(CollectionSize)
        {
            private List<File> CollectionList = new ArrayList<>();
            private int CurrentSize = 0;

        };


        //Randomize adding into collection otherwise if all full add to extra files
        boolean FileAdded = false;
        int prevNum = 0;
        if(numCollection != 0)
        {
            i = ThreadLocalRandom.current().nextInt(0, numCollection);
        }
        for (FileListType file : fileList)
        {
            FileAdded = false;
            prevNum = i;

            //Make sure the random number will not generate the same number as the previous generation
            if(numCollection > 1)
            {
                while(prevNum == i)
                {
                    i = ThreadLocalRandom.current().nextInt(0, numCollection);
                }
            }
            if(numCollection != 0)
            {
                while (i < numCollection && !FileAdded)
                {
                    if (CollectionList.get(i).enoughSpace(file.getFile()))
                    {
                        CollectionList.get(i).addCollection(file.getFile());
                        FileAdded = true;
                    }
                    else
                    {
                        i++;
                    }
                }
            }
            if(!FileAdded)
            {
                ExtraCollection.addCollection(file.getFile());
            }
        }

        //Shuffling the Collection and Extra lists after adding the files to the collection lists
        for (i = 0; i < numCollection; i++)
        {
            java.util.Collections.shuffle(CollectionList.get(i).getList());
        }
        java.util.Collections.shuffle(ExtraCollection.getList());


        //Displaying the Interface
        //Displays any files that do not exist
        if(!warningFiles.isEmpty())
        {
            DisplayInfo.displayTitle("Files that do not exist: ");
            for (String Warning : warningFiles)
            {
                System.out.println(Warning);
            }

        }


        //When user only enters 0 for collection
        if(!CollectionList.isEmpty() && numCollection == 0)
        {
            DisplayInfo.displayTitle("Empty Collection");
        }


        //Displays the collection List data
        else
        {
            for (i = 0; i < numCollection; i++)
            {
                displayCollector("Collection", i, CollectionList.get(i).getCurrentSize());
                CollectionList.get(i).printList();
            }
        }

        //Displays Extra Files list data
        if(!ExtraCollection.isEmpty() && numCollection == 0)
        {
            DisplayInfo.displayTitle("No file to Display, EMPTY");
            DisplayInfo.displayTitle("Source File does not have any valid file locations in it");
        }
        else
        {
            displayCollector("[# of Extra Files]", ExtraCollection.getNumFiles(), ExtraCollection.getCurrentSize());
            ExtraCollection.printList();
        }
    }


    /**
     * Method to display Collector Title
     * @param title given a user title
     * @param index given a user index for collector number
     * @param CollectorSize a collector size number in bytes
     */
    private void displayCollector(String title, int index, Long CollectorSize)
    {
        double CollectorDouble = (double)CollectorSize;
        System.out.printf("\n%s %d: %,.2f MiB (%,d bytes)\n", title, (index + 1), (CollectorDouble  / 1024 / 1024), CollectorSize);
        System.out.printf("**************************************************\n");
    }


    /**
     * Method Tester for program FileCollector
     * @param args user input arguments
     */
    public static void main(String[] args)
    {
        FileCollector fileCollector = new FileCollector();
        fileCollector.display(args);
    }
}

