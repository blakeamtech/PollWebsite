package Polls;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Poll{

    public Poll(String name, String question, List<String> choices) {
        this.choicesList = choices;
        this.pollTitle = name;
        this.questionText = question;
    }

    @JsonProperty
    private String pollTitle;

    @JsonProperty
    private String questionText;

    @JsonProperty
    private List<String> choicesList;

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

}
