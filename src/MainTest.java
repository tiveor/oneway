import org.junit.Test;
import tive.security.EncryptorHelper;
import tive.security.FileHelper;
import tive.security.WebPassword;
import tive.security.WebPasswordList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 18:09
 */
public class MainTest {

    @Test
    public void testStringEncryptDesencrypt() throws Exception {
        final String test = "this string will be encrypted and desencrypted";
        final String testEncrypted = EncryptorHelper.encrypt64(test);
        final String desEncrypted = EncryptorHelper.decrypt64(testEncrypted);
        assertEquals(test, desEncrypted);
    }

    @Test
    public void testStringEncryptDesencryptWithFiles() throws Exception {
        final String test = "this string will be encrypted and saved, readed and desencrypted";
        final String fileName = "file.dat";
        String testEncrypted = EncryptorHelper.encrypt64(test);
        FileHelper.saveToFile(testEncrypted, fileName);
        String testRecovered = FileHelper.readFromFile(fileName);
        String desEncrypted = EncryptorHelper.decrypt64(testRecovered);
        FileHelper.deleteFile(fileName);
        assertEquals(test, desEncrypted);
    }

    @Test
    public void testWebPasswordListEncryptDesencryptWithFiles() throws Exception {
        final String fileName = "file.dat";
        WebPasswordList newList = new WebPasswordList();
        newList.add(new WebPassword("hotmail.com", "pepe", "pepitoHM"));
        newList.add(new WebPassword("gmail.com", "pepe", "pepitoGM"));
        newList.add(new WebPassword("github.com", "pepe", "pepitoGH"));
        newList.add(new WebPassword("facebook.com", "pepe", "pepitoFB"));
        newList.saveToFile(fileName);
        WebPasswordList listFromFile = WebPasswordList.readFromFile(fileName);
        assertEquals(listFromFile.get(0).getEncPassword(), EncryptorHelper.encrypt64("pepitoHM"));
        assertEquals(listFromFile.get(1).getEncPassword(), EncryptorHelper.encrypt64("pepitoGM"));
        assertEquals(listFromFile.get(2).getEncPassword(), EncryptorHelper.encrypt64("pepitoGH"));
        assertEquals(listFromFile.get(3).getEncPassword(), EncryptorHelper.encrypt64("pepitoFB"));
    }

    @Test
    public void testWebPasswordListEncryptDesencryptChangingKey() throws Exception {
        final String fileName = "file.dat";
        WebPasswordList newList = new WebPasswordList();
        newList.add(new WebPassword("hotmail.com", "test1", "test11"));
        newList.add(new WebPassword("gmail.com", "test1", "test12"));
        newList.add(new WebPassword("github.com", "test1", "test13"));
        newList.add(new WebPassword("facebook.com", "test1", "test14"));
        newList.saveToFile(fileName);
        WebPasswordList listFromFile = WebPasswordList.readFromFile(fileName);
        assertEquals(listFromFile.get(0).getEncPassword(), EncryptorHelper.encrypt64("test11"));
        assertEquals(listFromFile.get(1).getEncPassword(), EncryptorHelper.encrypt64("test12"));
        EncryptorHelper.generateKey();
        assertTrue(listFromFile.get(2).getEncPassword() != EncryptorHelper.encrypt64("test13"));
        assertTrue(listFromFile.get(3).getEncPassword() != EncryptorHelper.encrypt64("test14"));
    }
}
