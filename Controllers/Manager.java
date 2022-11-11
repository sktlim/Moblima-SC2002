package Controllers;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface Manager {
    /** Read the contents of the given file.
     * Implementation of interface guarantees the imiplementation of this function
     * Declared as private as it is only called within the scope of the file
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List read(String fileName) throws IOException {return null;}

    /** Write a fixed content to the given file
     * Implementation of interface guarantees the implementation of this function
     * Declared as private as it is only called within the scope of the file
     * @param fileName
     * @param data
     * @throws IOException
     */
    private static void write(String fileName, List data) throws IOException  {}
}
