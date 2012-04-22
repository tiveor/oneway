package tive.security;

import java.io.*;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 18:05
 */
public class FileHelper {
    public static <T> void saveToFile(T t, String fileName) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (fileOut != null) fileOut.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static <T> T readFromFile(String fileName) {
        T t = null;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(fileName);
            in = new ObjectInputStream(fileIn);
            t = (T) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (fileIn != null) fileIn.close();
            } catch (IOException ignored) {
            }
        }
        return t;
    }

    public static void deleteFile(String fileName) {
        File f = new File(fileName);
        f.delete();
    }
}
