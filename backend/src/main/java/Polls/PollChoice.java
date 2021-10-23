package Polls;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PollChoice {

    @JsonProperty("choice")
    public String choice;

    @JsonProperty("choiceDescription")
    public String choiceDescription;

    public PollChoice(){};


}
