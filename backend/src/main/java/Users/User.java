package Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("emailAddress")
    public String emailAddress;

    @JsonProperty("hashedPassword")
    public String hashedPassword;

    @JsonProperty("votes")
    public Map<String, String> pollsVoted = new HashMap<>();

    public void vote(String votePin, String pollId){
        pollsVoted.putIfAbsent(pollId, votePin);
    }

    public BasicDBObject asDBObject(){
       return new BasicDBObject()
               .append("userId", this.userId)
               .append("emailAddress", this.emailAddress)
               .append("hashedPassword", this.hashedPassword)
               .append("fullName", this.fullName)
               .append("votes", pollsVoted);
    }
}
