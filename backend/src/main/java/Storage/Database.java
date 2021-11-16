package Storage;

import Polls.Poll;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final MongoClient mongoClient = new MongoClient(new MongoClientURI(Config.DB_STRING.value.toString()));


    public static void vote()

}
