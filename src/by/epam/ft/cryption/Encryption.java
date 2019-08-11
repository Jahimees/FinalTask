package by.epam.ft.cryption;

import java.util.Base64;

/**
 * Class for encrypt password
 */
public class Encryption {

    /**
     * crypt password
     * @param password
     * @return encrypted password
     */
    public static String encrypt(String password) {
        String encodeBytes = Base64.getEncoder().encodeToString((password).getBytes());
        return encodeBytes;
    }
}

