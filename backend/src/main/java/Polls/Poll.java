package Polls;

import Util.StringHelper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Poll implements Serializable {


    @JsonProperty("name")
    private String pollTitle;

    @JsonProperty("question")
    private String questionText;

    @JsonProperty("choices")
    private List<String> choicesList;

    private String pollId;

    private Poll(){
    }

    public Poll(String name, String question, List<String> choices) {
        this.choicesList = choices;
        this.pollTitle = name;
        this.questionText = question;
        this.pollId = StringHelper.randomID();
    }

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

    public String getPollId(){
        return this.pollId;
    }

    // could use an object mapper, but it would be longer in terms of mapping than simply creating a json object (imo)
    public JSONObject asJson(){
        return new JSONObject()
                .put("name", this.pollTitle)
                .put("question", this.questionText)
                .put("choices", this.choicesList)
                .put("pollId", this.pollId);
    }

    // it is a good practice to implement equals and hash when implementing serializable
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return pollTitle.equals(poll.pollTitle) &&
                questionText.equals(poll.questionText) &&
                choicesList.equals(poll.choicesList) &&
                pollId.equals(poll.pollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pollTitle, questionText, choicesList, pollId);
    }
}
