package ca.cmpt213.as1;

import java.io.File;
import java.io.*;

/**
 * Created by Jordan on 19/01/2017.
 */

/**
 * AnyExtFilter Class is supposed to: Accept files of user input extensions and return a boolean value if it is valid
 */
public class AnyExtFilter implements FilenameFilter
{
    private String Extension;


    /**
     * Constructor for String Extensions
     * @param Extension Extension string parameter to filter for certain extensions
     */
    public AnyExtFilter(String Extension)
    {
        this.Extension = Extension.toLowerCase();
    }


    /**
     * Method to accept if file is valid or not depending on extension
     * @param file given a file
     * @param Extensions check the extension type ends with is the same
     * @return boolean value if valid true, else false
     */
    @Override
    public boolean accept(File file, String Extensions)
    {
        if(file.isDirectory())
        {
            return (Extensions.toLowerCase().endsWith(Extension));
        }
        return false;

    }
}




