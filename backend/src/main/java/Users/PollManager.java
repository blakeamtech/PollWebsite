package Users;

import Objects.Poll;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PollManager {

    private static class Poll{

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

        public Poll(String name, String question, List<String> choices) {
            this.choicesList = choices;
            this.pollTitle = name;
            this.questionText = question;
            this.status = Objects.Poll.POLL_STATUS.CREATED;
        }

        @JsonProperty
        private String pollTitle;

        @JsonProperty
        private String questionText;

        @JsonProperty
        private List<String> choicesList;

        @JsonProperty
        private Objects.Poll.POLL_STATUS status;

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

    // key would be unique identifier (sessionId, wtv) and value would be the vote choice
    private static Map<String, String> submittedVotes = new HashMap<>();
    private static Poll pollInstance;


    public synchronized static void createPoll(String name, String question, List<String> choices){
        pollInstance = new Poll(name, question, choices);
    }

    public synchronized static void updatePoll(String name, String question, List<String> choices){

    }

    public synchronized static void clearPoll(){}

    public synchronized static void closePoll(){}

    public synchronized static void runPoll(){}

    public synchronized static void releasePoll(){}

    public synchronized static void unreleasePoll(){}

    /**
     * Participant submits vote. Should check if participant already voted, in that case update their choice
     * @param participant
     * @param choice
     */
    public synchronized static void vote(Participant participant, String choice){
        submittedVotes.put(participant.getSessionId(), choice);
    }

    public synchronized static Hashtable<String, Integer> getPollResults(){
        Hashtable<String, Integer> toReturn = new Hashtable<>();

        submittedVotes.values().parallelStream().forEach(
                item->{
                    int val = (toReturn.contains(item)) ? toReturn.get(item) : 0;
                    toReturn.put(item, val+1);
                }
        );

        return toReturn;
    }

    public synchronized static void downloadPollDetails(PrintWriter output, String fileName){

    }
}
