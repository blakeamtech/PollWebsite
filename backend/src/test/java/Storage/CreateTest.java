package Storage;

import Polls.Poll;
import Storage.Entities.Choice;
import Storage.Entities.Vote;
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
     * Test responsible for checking if a poll is correctly added to the database.
     */
    @Test
    public void createPollTest()
    {
        try
        {
            Poll poll = new Poll();
            String id = getToken();
            poll.setPollId(id);
            poll.setPollTitle("Favorite Foods");
            poll.setQuestionText("What is your favorite food?");
            poll.setEmail("shadowalessandro@hotmail.com");
            poll.setPollStatus("created");

            MysqlJDBC.getInstance().insertPoll(poll);
            Poll found = MysqlJDBC.getInstance().selectPoll(id);
            assertEquals(poll.getQuestionText(), found.getQuestionText());
        }
        catch (Exception e)
        {
            fail("The poll has not been added to the database!");
        }
    }

    /**
     * Test responsible for checking if a choice is correctly added to the database.
     */
    @Test
    public void createChoiceTest()
    {
        try
        {
            String pollId = MysqlJDBC.getInstance().selectAllPolls().get(0).getPollId();
            Choice choice = new Choice();
            choice.setChoice("Pizza");
            choice.setPollId(pollId);

            MysqlJDBC.getInstance().insertChoice(choice);
            Choice found = MysqlJDBC.getInstance().selectPollChoices(pollId).get(0);
            assertEquals(choice.getChoice(), found.getChoice());
        }
        catch (Exception e)
        {
            fail("The choice has not been added to the database!");
        }
    }

    /**
     * Test responsible for checking if a vote is correctly added to the database.
     */
    @Test
    public void createVoteTest()
    {
        try
        {
            String pollId = MysqlJDBC.getInstance().selectAllPolls().get(0).getPollId();
            Vote vote = new Vote();
            vote.setPollId(pollId);
            vote.setChoiceId("1");
            String pin = getToken();
            vote.setPIN(pin);

            MysqlJDBC.getInstance().insertVote(vote);
            Vote found = MysqlJDBC.getInstance().selectVote("1");
            assertEquals(vote.getPIN(), found.getPIN());
        }
        catch (Exception e)
        {
            fail("The vote has not been added to the database!");
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
