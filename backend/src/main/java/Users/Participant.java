package Users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Participant {

    @JsonProperty
    private String sessionId;

    public Participant(){
        this.sessionId = UUID.randomUUID().toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
