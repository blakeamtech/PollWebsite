package Storage.Entities;

import org.json.JSONObject;

public class Choice {
    private String choiceID;
    private String pollId;
    private String choice;

    public Choice(){}

    public Choice(String choiceID, String pollId, String choice) {
        this.choiceID = choiceID;
        this.pollId = pollId;
        this.choice = choice;
    }

    public String getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(String choiceID) {
        this.choiceID = choiceID;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public JSONObject asJson(){
        JSONObject jsonToReturn = new JSONObject();
        jsonToReturn.put("choice", this.choice);
        jsonToReturn.put("choiceId", this.choiceID);
        return jsonToReturn;
    }
}
