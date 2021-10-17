package Users;

import Exceptions.*;
import Polls.Poll;
import Util.SessionManager;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PollManager {

    public static enum POLL_STATUS{
        CREATED("created"),
        RUNNING("running"),
        RELEASED("released"),
        CLOSED("closed"),
        CLEARED("cleared");

        private final String value;

        POLL_STATUS(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }

    // key would be unique identifier (sessionId, wtv) and value would be the vote choice
    private static final Map<String, String> submittedVotes = new HashMap<>();
    private static Poll pollInstance;
    private static long pollReleasedTimestamp;
    private static POLL_STATUS currentStatus;

    public synchronized static void createPoll(String name, String question, List<String> choices)
            throws AssignmentException {

        // if there exists a poll, can't create a new one
        if(pollInstance != null){
            throw new PollAlreadyInSystemException();
        }

        pollInstance = new Poll(name, question, choices);
        currentStatus = POLL_STATUS.CREATED;
    }

    public synchronized static void updatePoll(String name, String question, List<String> choices) throws InvalidPollStateException {
        // can only update a poll if it's already running
        if(pollInstance == null || (currentStatus != POLL_STATUS.RUNNING && currentStatus != POLL_STATUS.CREATED))
            throw new InvalidPollStateException(currentStatus.value, "update");

        pollInstance = new Poll(name, question, choices);
        clearChoices();
        currentStatus = POLL_STATUS.CREATED;
    }

    private static void clearChoices() {
        synchronized (submittedVotes){
            submittedVotes.clear();
        }
    }

    public synchronized static void clearPoll() throws InvalidPollStateException {
        if(pollInstance == null)
            throw new InvalidPollStateException("null", "close");

        if(currentStatus != POLL_STATUS.RELEASED && currentStatus != POLL_STATUS.RUNNING )
            throw new InvalidPollStateException(currentStatus.value, "clear");
    }

    public synchronized static void closePoll() throws InvalidPollStateException {
        if(pollInstance == null || currentStatus != POLL_STATUS.RELEASED)
            throw new InvalidPollStateException(currentStatus.value, "close");

        currentStatus = POLL_STATUS.CLOSED;
    }

    public synchronized static void runPoll(){

    }

    public synchronized static void releasePoll() throws InvalidPollStateException {
        pollReleasedTimestamp = System.nanoTime();

        if(pollInstance == null || currentStatus != POLL_STATUS.RUNNING){
            throw new InvalidPollStateException(currentStatus.value, "release");
        }

        currentStatus = POLL_STATUS.RELEASED;
    }

    public synchronized static void unreleasePoll() throws InvalidPollStateException {
        if (pollInstance == null || currentStatus != POLL_STATUS.RELEASED){
            throw new InvalidPollStateException(currentStatus.value, "unrelease");
        }

        currentStatus = POLL_STATUS.RUNNING;
    }

    public synchronized static void vote(HttpSession httpSession, String choice) throws InvalidChoiceException {
        if(choice.isBlank() || choice.isEmpty() || !PollManager.validateChoice(choice))
            throw new InvalidChoiceException();

        submittedVotes.put(httpSession.getId(), choice);
        SessionManager.vote(httpSession, choice);
    }

    public static Hashtable<String, Integer> getPollResults(){
        Hashtable<String, Integer> toReturn = new Hashtable<>();

        synchronized (submittedVotes){
            submittedVotes.values().stream().sequential().forEach(
                    item->{
                        int val = (toReturn.contains(item)) ? toReturn.get(item) : 0;
                        toReturn.put(item, val+1);
                    }
            );

        }

        return toReturn;
    }

    public synchronized static void downloadPollDetails(PrintWriter output, String fileName) throws PollIsNotReleasedException {
        if(currentStatus == POLL_STATUS.RELEASED){
            output.println(new JSONObject(getPollResults()));
        }

        throw new PollIsNotReleasedException();
    }

    public static String getPollTitle(){
        return pollInstance.getPollTitle();
    }


    public synchronized static boolean validateChoice(String choice) {
        return pollInstance.getChoicesList().contains(choice);
    }

    public static long getPollReleasedTimestamp(){
        return pollReleasedTimestamp;
    }


}
