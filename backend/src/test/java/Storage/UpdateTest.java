package Storage;

import Users.User;
import org.junit.Test;

import java.security.MessageDigest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UpdateTest
{
    /**
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void updateUserFullNameTest()
    {
        try
        {
            User user = MysqlJDBC.getInstance().selectUser("1");
            user.setFullName("Alexander Bowl");
            MysqlJDBC.getInstance().updateUser(user);

            User found = MysqlJDBC.getInstance().selectUser("1");
            assertEquals(user.fullName, found.fullName);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
        }
    }

    /**
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void updateUserEmailTest()
    {
        try
        {
            User user = MysqlJDBC.getInstance().selectUser("1");
            user.setEmailAddress("alessandromciotola@gmail.com");
            MysqlJDBC.getInstance().updateUser(user);

            User found = MysqlJDBC.getInstance().selectUser("1");
            assertEquals(user.emailAddress, found.emailAddress);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
        }
    }

    /**
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void updatePasswordTest()
    {
        try
        {
            User user = MysqlJDBC.getInstance().selectUser("1");
            user.setHashedPassword(sha256("abc"));
            MysqlJDBC.getInstance().updateUser(user);

            User found = MysqlJDBC.getInstance().selectUser("1");
            assertEquals(user.hashedPassword, found.hashedPassword);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
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
}
