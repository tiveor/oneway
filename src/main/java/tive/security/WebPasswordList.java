package tive.security;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 16:51
 */
public class WebPasswordList extends ArrayList<WebPassword> implements Serializable {

    public void saveToFile(String fileName) {
        FileHelper.saveToFile(this, fileName);
    }

    public static WebPasswordList readFromFile(String fileName) {
        return FileHelper.readFromFile(fileName);
    }
}
