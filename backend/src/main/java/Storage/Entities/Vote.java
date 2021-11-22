package Storage.Entities;

public class Vote {
    private String voteId = "";
    private String PIN = "";
    private String choiceId = "";
    private String pollId = "";

    public Vote() {}

    public Vote(String voteId, String PIN, String choiceId, String pollId) {
        this.voteId = voteId;
        this.PIN = PIN;
        this.choiceId = choiceId;
        this.pollId = pollId;
    }

    public String getPollId(){
        return pollId;
    }

    public void setPollId(String id) { this.pollId = id; }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }
}
