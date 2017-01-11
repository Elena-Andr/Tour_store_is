package ru.innopolis.tourstore.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Helper class for hashing password string
 */
public class PasswordAuthentication {
    private static final Logger LOG = LoggerFactory.getLogger(PasswordAuthentication.class);

    /**
     * Method hashes password with specific salt
     * @param password
     * @param salt
     * @return String representation of hashes and salt password
     */
    public static String hashPassword(String password, String salt){
        String hash = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(salt.getBytes(Charset.forName("UTF-8")));
            byte[] data = messageDigest.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< data.length ;i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hash = sb.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }

        return hash;
    }

    /**
     * Method generates random salt
     * @return String representation of salt
     */
    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[5];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< salt.length ;i++)
        {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
