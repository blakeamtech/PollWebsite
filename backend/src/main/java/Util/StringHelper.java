package Util;

import Storage.Config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class StringHelper {


    public synchronized static String randomID(){
        StringBuilder toReturn = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i =0 ;i < 10; i++){
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            toReturn.append(allowedChars.charAt(index));
        }

        return toReturn.toString();
    }

    public static String randomPin(){
        return Integer.toString(ThreadLocalRandom.current().nextInt(100000, 999999));
    }

    public static String sha256(final String base)
    {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
