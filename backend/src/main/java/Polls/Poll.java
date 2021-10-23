package Polls;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Poll{

    public Poll(){

    }

    public Poll(String name, String question, List<PollChoice> choices) {
        this.choicesList = choices;
        this.pollTitle = name;
        this.questionText = question;
    }

    @JsonProperty("name")
    private String pollTitle;

    @JsonProperty("question")
    private String questionText;

    @JsonProperty("choices")
    private List<PollChoice> choicesList;

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

    public List<PollChoice> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<PollChoice> choicesList) {
        this.choicesList = choicesList;
    }

}
