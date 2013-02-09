package tive.security;

import java.io.Serializable;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 16:41
 */
public class Password implements Serializable {
    private String encPassword;

    public Password(String password) {
        try {
            encPassword = EncryptorHelper.encrypt64(password);
        } catch (Exception e) {
            encPassword = null;
            e.printStackTrace();
        }
    }

    public String getEncPassword() {
        return encPassword;
    }

    @Override
    public String toString() {
        return "tive.security.Password{" +
                "encPassword='" + encPassword + '\'' +
                '}';
    }
}
