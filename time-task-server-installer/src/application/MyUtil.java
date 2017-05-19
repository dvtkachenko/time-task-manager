package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by diman on 18.05.2017.
 */
public class MyUtil {

    public static void CopyFileFromTo(String pathFrom, String pathTo) {
        FileInputStream inFile = null;
        FileOutputStream outFile = null;

            try  {
                inFile = new FileInputStream(pathFrom);
                outFile = new FileOutputStream(pathTo);

                int c;

                while ((c = inFile.read()) != -1) {
                    outFile.write(c);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (inFile != null) inFile.close();
                    if (outFile != null) outFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void SaveToFile(String fullFileName, List<String> rowsToFile) {
        try(FileWriter writer = new FileWriter(fullFileName, false))
        {
           for (String row : rowsToFile) {
               writer.write(row);
               writer.append('\n');
               writer.flush();
           }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
