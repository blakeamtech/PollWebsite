package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Poll {

    public enum POLL_STATUS{
        CREATED("created"),
        RUNNING("running"),
        CLEARED("cleared");

        private final String value;

        POLL_STATUS(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }

    @JsonProperty
    private String pollTitle;

    @JsonProperty
    private String questionText;

    @JsonProperty
    private List<String> choicesList;

    @JsonProperty
    private POLL_STATUS status;

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
