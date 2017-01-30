package ca.cmpt213.as1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 19/01/2017.
 */

/**
 * Collection class to store as an array of data type file, max size and current size for each collection
 */
public class Collection
{
    private List<File> CollectionList = new ArrayList<>();
    private long MaxSize;
    private long CurrentSize = 0;


    /**
     * Constructor of the Collection, sets maxsize
     * @param MaxSize the maximum size of the collection List
     */
    public Collection (long MaxSize)
    {
            this.MaxSize = MaxSize;
    }


    /**
     * Method to check if there is enough space in the CollectionList to store the currently checked file then returns if its true or not
     * @param sourceFile the source file of the sources.txt
     * @return a boolean value if it has enough space true or not false
     */
    public boolean enoughSpace(File sourceFile)
    {
        if((CurrentSize + sourceFile.length()) <= MaxSize)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * Method to add a file to the collection List
     * @param sourceFile a file type of the source File
     */
    public void addCollection(File sourceFile)
    {
        CollectionList.add(sourceFile);
        CurrentSize = CurrentSize + sourceFile.length();
    }


    /**
     * Method to print out the CollectionList
     */
    public void printList()
    {
        for (File item: CollectionList)
        {
            System.out.println(item);
        }
    }


    /**
     * Method to return the CollectionList
     * @return CollectionList
     */
    public List<File> getList()
    {
        return CollectionList;
    }


    /**
     * Method to obtain current size of collection type in bytes
     * @return the size of the collection of all files in bytes
     */
    public Long getCurrentSize()
    {
        return CurrentSize;
    }


    /**
     * Method to obtain number of files From collection type CollectionList
     * @return CollectionList which is the list of files for this collection
     */
    public int getNumFiles()
    {
        return CollectionList.size();
    }


    /**
     * Method to obtain a boolean value if this class list is empty or not
     * @return boolean value if empty or not
     */
    public boolean isEmpty()
    {
        if(CollectionList.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
