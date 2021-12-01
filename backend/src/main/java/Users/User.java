package Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("emailAddress")
    public String emailAddress;

    @JsonProperty("hashedPassword")
    public String hashedPassword;

    @JsonProperty("verified")
    public boolean verified;

    @JsonProperty("token")
    public String token;

    private List<String> userPins = new ArrayList<>();

    private List<String> userPollIds = new ArrayList<>();

    public void addPin(String pin){
        this.userPins.add(pin);
    }

    public void addPollId(String pollId) { this.userPollIds.add(pollId); }

    public boolean getVerified() {
        return verified;
    }

    public String getToken() {
        return token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject asJson(){
       return new JSONObject()
               .put("userId", this.userId)
               .put("emailAddress", this.emailAddress)
               .put("hashedPassword", this.hashedPassword)
               .put("fullName", this.fullName);
    }
}
