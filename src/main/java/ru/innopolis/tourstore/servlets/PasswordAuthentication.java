package ru.innopolis.tourstore.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Helper class for hashing password string
 */
public class PasswordAuthentication {
    private static final Logger LOG = LoggerFactory.getLogger(PasswordAuthentication.class);

    public static byte[] hashPassword(String password, String salt){
        byte[] hash = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(salt.getBytes("UTF-8"));
            hash = messageDigest.digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return hash;
    }
}
