package Users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;

public class User {

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("emailAddress")
    public String emailAddress;

    @JsonProperty("hashedPassword")
    public String hashedPassword;

    public BasicDBObject asDBObject(){
        return new BasicDBObject()
                .append("userId", this.userId)
                .append("fullName", this.fullName)
                .append("emailAddress", this.emailAddress)
                .append("hashedPassword", this.hashedPassword);
    }
}
