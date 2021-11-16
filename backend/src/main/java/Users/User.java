package Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import org.json.JSONObject;

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

    private List<String> userPins = new ArrayList<>();

    private List<String> userPollIds = new ArrayList<>();

    public void addPin(String pin){
        this.userPins.add(pin);
    }

    public void addPollId(String pollId) { this.userPollIds.add(pollId); }

    public JSONObject asJson(){
       return new JSONObject()
               .put("userId", this.userId)
               .put("emailAddress", this.emailAddress)
               .put("hashedPassword", this.hashedPassword)
               .put("fullName", this.fullName);
    }
}
