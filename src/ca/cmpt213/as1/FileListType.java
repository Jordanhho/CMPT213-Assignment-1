package ca.cmpt213.as1;

import java.io.File;

/**
 * Created by Nova on 25/01/2017.
 */

/**
 * FileListType class to store as a data type with a file location and file size with a comparator to sort the list
 */
public class FileListType implements Comparable<FileListType>
{
    private File sourceFile;
    private Long fileSize;


    /**
     * FileListType Constructor
     * @param sourceFile the sourceFile location
     * @param fileSize the file size of this sourceFile
     */
    public FileListType(File sourceFile, Long fileSize)
    {
        this.sourceFile = sourceFile;
        this.fileSize = fileSize;
    }


    /**
     * Method to obtain sourceFile
     * @return sourceFile
     */
    public File getFile()
    {
        return sourceFile;
    }


    /**
     * Method to obtain FileSize of sourceFile
     * @return fileSize
     */
    public long getFileSize()
    {
        return fileSize;
    }


    /**
     * Method to override comparator to obtain a sorted file list
     * @param other given a FileListType data, sort it
     * @return the sorted filelist
     */
    @Override
    public int compareTo(FileListType other)
    {
        return fileSize.compareTo(other.fileSize);
    }
}