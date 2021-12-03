package Storage;

import Users.User;
import org.junit.Test;

import java.security.MessageDigest;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateTest
{
    /**
     * Test responsible for checking if a user is correctly added to the database.
     */
    @Test
    public void createUserTest()
    {
        try
        {
            User user = new User();
            user.setFullName("Alessandro Ciotola");
            user.setEmailAddress("shadowalessandro@hotmail.com");
            user.setToken(getToken());
            user.setHashedPassword(sha256("123"));

            MysqlJDBC.getInstance().insertUser(user);
            User found = MysqlJDBC.getInstance().selectUserFromEmail("shadowalessandro@hotmail.com");
            assertEquals(user.fullName, found.fullName);
        }
        catch (Exception e)
        {
            fail("The user has not been added to the database!");
        }
    }

    /**
     * Method responsible for encrypting the password.
     *
     * @param base
     * @return
     */
    private static String sha256(final String base)
    {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Method responsible for generating a random token.
     *
     * @return
     */
    private String getToken()
    {
        StringBuilder str = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i = 0 ; i < 16; i++){
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            str.append(allowedChars.charAt(index));
        }

        return str.toString();
    }
}
