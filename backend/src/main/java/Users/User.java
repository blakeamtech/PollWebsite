package Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("emailAddress")
    public String emailAddress;

    @JsonProperty("hashedPassword")
    public String hashedPassword;

    private List<String> pollsVoted = new ArrayList<>();

    public void vote(String pin){
        this.pollsVoted.add(pin);
    }

    public BasicDBObject asDBObject(){
       return new BasicDBObject()
               .append("userId", this.userId)
               .append("emailAddress", this.emailAddress)
               .append("hashedPassword", this.hashedPassword)
               .append("fullName", this.fullName);
    }
}
