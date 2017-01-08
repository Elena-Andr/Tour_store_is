package ru.innopolis.tourstore.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Helper class for hashing password string
 */
public class PasswordAuthentication {
    private static final Logger LOG = LoggerFactory.getLogger(PasswordAuthentication.class);

    /**
     *
     * @param password
     * @param salt
     * @return
     */
    public static byte[] hashPassword(String password, byte[] salt){
        byte[] hash = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(salt);
            hash = messageDigest.digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return hash;
    }

    /**
     * Method generates random bytes
     * @return
     */
    public static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[20];
        random.nextBytes(salt);

        return salt;
    }
}
