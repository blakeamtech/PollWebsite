package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Poll {

    @JsonProperty
    private String pollTitle;

    @JsonProperty
    private String questionText;

    @JsonProperty
    private List<String> choicesList;

    @JsonProperty
    private String status;

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<String> choicesList) {
        this.choicesList = choicesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
