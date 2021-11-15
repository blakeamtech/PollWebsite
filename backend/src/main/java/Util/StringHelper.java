package Util;

import Storage.Config;

import java.util.Random;
import java.util.UUID;

public class StringHelper {

    private static final Random r = new Random();

    public synchronized static String randomID(){
        StringBuilder toReturn = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i =0 ;i < 10; i++){
            int index = r.nextInt(allowedChars.length());
            toReturn.append(allowedChars.charAt(index));
        }

        return toReturn.toString();
    }

}
