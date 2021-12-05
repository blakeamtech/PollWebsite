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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (verified != user.verified) return false;
        if (!userId.equals(user.userId)) return false;
        if (!fullName.equals(user.fullName)) return false;
        if (!emailAddress.equals(user.emailAddress)) return false;
        if (!hashedPassword.equals(user.hashedPassword)) return false;
        if (!token.equals(user.token)) return false;
        if (!userPins.equals(user.userPins)) return false;
        return userPollIds.equals(user.userPollIds);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + hashedPassword.hashCode();
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + token.hashCode();
        result = 31 * result + userPins.hashCode();
        result = 31 * result + userPollIds.hashCode();
        return result;
    }
}
