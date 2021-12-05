package Storage;

import Users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MysqlJDBCTest {

    private final User user = new User();

    @BeforeEach
    public void setUp(){
        user.setFullName("Alessandro Ciotola");
        user.setEmailAddress("shadowalessandro@hotmail.com");
        user.setToken(getToken());
        user.setHashedPassword(sha256("123"));
    }

    @AfterEach
    public void tearDown() throws SQLException, ClassNotFoundException {
        MysqlJDBC.getInstance().deleteUser(user.userId);
    }

    @Test
    public void createUserTest(){
        try
        {
            MysqlJDBC.getInstance().insertUser(user);
            User found = MysqlJDBC.getInstance().selectUserFromEmail("shadowalessandro@hotmail.com");
            Assert.assertEquals(user.fullName, found.fullName);
        }
        catch (Exception e)
        {
            Assert.fail("The user has not been added to the database!");
        }
    }

    /**
     * Method responsible for generating a random token.
     *
     * @return
     */
    private static String getToken()
    {
        StringBuilder str = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i = 0 ; i < 16; i++){
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            str.append(allowedChars.charAt(index));
        }

        return str.toString();
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
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void deleteUserTest()
    {
        try
        {
            User userToDeleteAfter = new User();

            userToDeleteAfter.setFullName("Test User");
            userToDeleteAfter.setEmailAddress("testemail@hotmail.com");
            userToDeleteAfter.setToken(getToken());
            userToDeleteAfter.setHashedPassword(sha256("testpw"));

            MysqlJDBC.getInstance().insertUser(userToDeleteAfter);

            User found = MysqlJDBC.getInstance().selectUser(userToDeleteAfter.userId);
            assertNotNull(found);
            assertEquals(userToDeleteAfter.fullName, found.fullName);
            assertEquals(userToDeleteAfter.emailAddress, found.emailAddress);
            assertEquals(userToDeleteAfter.token, found.token);
            assertEquals(userToDeleteAfter.hashedPassword, found.hashedPassword);

            MysqlJDBC.getInstance().deleteUser(userToDeleteAfter.userId);

            User notFound = MysqlJDBC.getInstance().selectUser(userToDeleteAfter.userId);

            assertNull(notFound);
        }
        catch (Exception e)
        {
            fail("Could not delete the user in the database!");
        }
    }

    @Test
    public void selectUserByIdTest() throws SQLException, ClassNotFoundException {
        MysqlJDBC.getInstance().insertUser(user);
        User toReadAndVerify = MysqlJDBC.getInstance().selectUser(user.userId);
        assertEquals(user.fullName, toReadAndVerify.fullName);
        assertEquals(user.emailAddress, toReadAndVerify.emailAddress);
        assertEquals(user.hashedPassword, toReadAndVerify.hashedPassword);
        assertEquals(user.token, toReadAndVerify.token);
    }

    @Test
    public void selectUserByEmailTest() throws SQLException, ClassNotFoundException {
        MysqlJDBC.getInstance().insertUser(user);
        User toReadAndVerify = MysqlJDBC.getInstance().selectUserFromEmail(user.emailAddress);
        assertEquals(user.fullName, toReadAndVerify.fullName);
        assertEquals(user.emailAddress, toReadAndVerify.emailAddress);
        assertEquals(user.hashedPassword, toReadAndVerify.hashedPassword);
        assertEquals(user.token, toReadAndVerify.token);
    }

    @Test
    public void selectUserByTokenTest() throws SQLException, ClassNotFoundException {
        MysqlJDBC.getInstance().insertUser(user);
        User toReadAndVerify = MysqlJDBC.getInstance().selectUserFromToken(user.token);
        assertEquals(user.fullName, toReadAndVerify.fullName);
        assertEquals(user.emailAddress, toReadAndVerify.emailAddress);
        assertEquals(user.hashedPassword, toReadAndVerify.hashedPassword);
        assertEquals(user.token, toReadAndVerify.token);
    }

    @Test
    public void selectAllUsersTest() throws Exception{
        MysqlJDBC.getInstance().insertUser(user);
        List<User> users = MysqlJDBC.getInstance().selectAllUsers();
        assertEquals(users.get(0), user);
    }

    /**
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void updateUserFullNameTest()
    {
        try
        {
            MysqlJDBC.getInstance().insertUser(user);
            User userToVerify = MysqlJDBC.getInstance().selectUser(user.userId);
            userToVerify.setFullName("Alexander Bowl");
            MysqlJDBC.getInstance().updateUser(userToVerify);

            User found = MysqlJDBC.getInstance().selectUser(userToVerify.userId);
            assertEquals(userToVerify.fullName, found.fullName);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
        }
    }

    @Test
    public void updateUserEmailTest()
    {
        try
        {
            MysqlJDBC.getInstance().insertUser(user);
            User userToVerify = MysqlJDBC.getInstance().selectUser(user.userId);
            userToVerify.setEmailAddress("alessandromciotola@gmail.com");
            MysqlJDBC.getInstance().updateUser(userToVerify);

            User found = MysqlJDBC.getInstance().selectUser(user.userId);
            assertEquals(userToVerify.emailAddress, found.emailAddress);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
        }
    }

    @Test
    public void updatePasswordTest()
    {
        try
        {
            MysqlJDBC.getInstance().insertUser(user);
            User userToVerify = MysqlJDBC.getInstance().selectUser(user.userId);
            userToVerify.setHashedPassword(sha256("abc"));
            MysqlJDBC.getInstance().updateUser(userToVerify);

            User found = MysqlJDBC.getInstance().selectUser(user.userId);
            assertEquals(userToVerify.hashedPassword, found.hashedPassword);
        }
        catch (Exception e)
        {
            fail("Could not update the user in the database!");
        }
    }
}