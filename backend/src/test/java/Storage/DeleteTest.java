package Storage;

import Users.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DeleteTest
{
    /**
     * Test responsible for checking if updating a user in the database is successful.
     */
    @Test
    public void deleteUserTest()
    {
        try
        {
            MysqlJDBC.getInstance().deleteUser("1");

            User notFound = MysqlJDBC.getInstance().selectUser("1");

            assertEquals(null, notFound);
        }
        catch (Exception e)
        {
            fail("Could not delete the user in the database!");
        }
    }
}
