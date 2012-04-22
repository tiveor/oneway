package tive.security;

import java.io.Serializable;

/**
 * User: Tive
 * Date: 21/04/12
 * Time: 16:45
 */
public class WebPassword extends Password implements Serializable {

    private String webpage;
    private String login;

    public WebPassword(String webpage, String login, String password) {
        super(password);
        this.webpage = webpage;
        this.login = login;
    }

    public String getWebpage() {
        return webpage;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "tive.security.WebPassword{" +
                "webpage='" + webpage + '\'' +
                ", login='" + login + '\'' +
                "} " + super.toString();
    }
}
