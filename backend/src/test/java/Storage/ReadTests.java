package Storage;

import Users.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReadTests
{
    /**
     * Test responsible for checking if all users are correctly retrieved from the database.
     */
    @Test
    public void selectAllUsersTest()
    {
        try
        {
            List<User> found = MysqlJDBC.getInstance().selectAllUsers();
            assertEquals(1, found.size());
        }
        catch (Exception e)
        {
            fail("Could not retrieve all users from the database!");
        }
    }

    /**
     * Test responsible for checking if a user is correctly retrieved from the database.
     */
    @Test
    public void selectUserTest()
    {
        try
        {
            User found = MysqlJDBC.getInstance().selectUser("1");
            assertEquals("Alessandro Ciotola", found.fullName);
        }
        catch (Exception e)
        {
            fail("Could not retrieve user from the database!");
        }
    }

    /**
     * Test responsible for checking if a user is correctly retrieved from the database.
     */
    @Test
    public void selectUserEmailTest()
    {
        try
        {
            User found = MysqlJDBC.getInstance().selectUserFromEmail("shadowalessandro@hotmail.com");
            assertEquals("Alessandro Ciotola", found.fullName);
        }
        catch (Exception e)
        {
            fail("Could not retrieve user from the database!");
        }
    }

    /**
     * Test responsible for checking if a user is correctly retrieved from the database.
     */
    @Test
    public void selectUserTokenTest()
    {
        try
        {
            User found = MysqlJDBC.getInstance().selectUserFromToken("zbwtzrasfyzg7fyy");
            assertEquals("Alessandro Ciotola", found.fullName);
        }
        catch (Exception e)
        {
            fail("Could not retrieve user from the database!");
        }
    }
}
