package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 18.05.2017.
 *
 * This is utils class
 *
 */

public class MyUtil {

    public static void copyFileFromTo(String pathFrom, String pathTo) throws Exception {

    	// i do not use buffered reading and saving 
    	// just for demonstrate how installation progress bar works :)
    	//
//    	BufferedInputStream inFile = null;
//    	BufferedOutputStream outFile = null;
        FileInputStream inFile = null;
        FileOutputStream outFile = null;

            try  {
//                inFile = new BufferedInputStream(new FileInputStream(pathFrom));
//                outFile = new BufferedOutputStream(new FileOutputStream(pathTo));
                inFile = new FileInputStream(pathFrom);
                outFile = new FileOutputStream(pathTo);

                int c;

                while ((c = inFile.read()) != -1) {
                    outFile.write(c);
                }
                outFile.flush();

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            finally {
                try {
                    if (inFile != null) inFile.close();
                    if (outFile != null) outFile.close();
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
    }

    public static void saveToFile(String fullFileName, List<String> rowsToFile) throws Exception {
        try(FileWriter writer = new FileWriter(fullFileName, false))
        {
           for (String row : rowsToFile) {
               writer.write(row);
               writer.append('\n');
               writer.flush();
           }
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
