package Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {

    private static Connection connection;

    public static void init() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(String.format(Config.DB_STRING.value.toString(), Config.DB_PORT.value.toString())
                            , Config.DB_USERNAME.value.toString(), Config.DB_PW.value.toString());
        }
    }



}
