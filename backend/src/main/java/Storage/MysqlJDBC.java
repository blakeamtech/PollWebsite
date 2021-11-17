package Storage;

import Polls.Poll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlJDBC {

    private static Connection connection;

    private static final String INSERT_POLL_QUERY = "INSERT INTO Polls (pollId, title, question) values (?, ?, ?)";
    private static final String INSERT_CHOICE_QUERY = "INSERT INTO Choice (pollId, choice) values (?, ?)";

    public static void init() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection(String.format(Config.DB_STRING.value.toString(), Config.DB_PORT.value.toString())
                            , Config.DB_USERNAME.value.toString(), Config.DB_PW.value.toString());
        }
    }

    public synchronized static void insertPoll(Poll poll) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_POLL_QUERY);
        statement.setString(1, poll.getPollId());
        statement.setString(2, poll.getPollTitle());
        statement.setString(3, poll.getQuestionText());
    }



}
