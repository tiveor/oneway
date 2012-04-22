package tive.security;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 18:19
 */
public class EncryptorHelper {

    private final static String key = "This a generic key to encrypt all that you want";
    private final static String charset = "UTF-8";
    private final static String algorithm = "DESede";
    private static String currentKey = generateKey();

    public static String generateKey() {
        currentKey = System.currentTimeMillis() + key;
        return currentKey;
    }

    private static SecretKey getSecret() throws Exception {
        DESedeKeySpec keySpec = new DESedeKeySpec(currentKey.getBytes(charset));
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
        return factory.generateSecret(keySpec);
    }

    private static byte[] toBytesFromCharset(String word) throws UnsupportedEncodingException {
        return word.getBytes(charset);
    }

    private static String toStringFromCharset(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, charset);
    }

    public static String encrypt64(String password) throws Exception {
        return Base64.encode(encrypt(toBytesFromCharset(password)));
    }

    public static byte[] encrypt(byte[] password) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, getSecret());
        return cipher.doFinal(password);
    }

    public static String decrypt64(String encryptedPassword) throws Exception {
        return toStringFromCharset(decrypt(Base64.decode(encryptedPassword)));
    }

    public static byte[] decrypt(byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, getSecret());
        return cipher.doFinal(encrypted);
    }

}
