package Util;

import Storage.Config;

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

}
