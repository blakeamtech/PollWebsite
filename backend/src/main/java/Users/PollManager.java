package Users;

import Exceptions.*;
import Polls.Poll;
import Util.SessionManager;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PollManager {

    public enum POLL_STATUS {
        CREATED("created"),
        RUNNING("running"),
        RELEASED("released"),
        CLOSED("closed");

        private final String value;

        POLL_STATUS(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    // key would be unique identifier (sessionId, wtv) and value would be the vote choice
    private static final Map<String, String> submittedVotes = new HashMap<>();
    private static final Map<String, Integer> voteCount = new HashMap<>();
    private static Poll pollInstance;
    private static long pollReleasedTimestamp;
    private static POLL_STATUS currentStatus = POLL_STATUS.CLOSED;

    public synchronized static void createPoll(String name, String question, List<String> choices)
            throws AssignmentException {


        // if there exists a poll, can't create a new one
        if (pollInstance != null && currentStatus != POLL_STATUS.CLOSED) {
            throw new PollAlreadyInSystemException();
        }

        initAndCreatePoll(name, question, choices);

    }

    public synchronized static void updatePoll(String name, String question, List<String> choices) throws InvalidPollStateException {
        // can only update a poll if it's already running
        if (pollInstance == null || (currentStatus != POLL_STATUS.CREATED && currentStatus != POLL_STATUS.RUNNING))
            throw new InvalidPollStateException(currentStatus.value, "update");

        pollInstance = new Poll(name, question, choices);
        currentStatus = POLL_STATUS.CREATED;
        clearChoices();
        addChoices(choices);
    }


    public synchronized static void clearPoll() throws InvalidPollStateException {
        if (pollInstance == null)
            throw new InvalidPollStateException("null", "close");

        if (currentStatus != POLL_STATUS.RELEASED && currentStatus != POLL_STATUS.RUNNING)
            throw new InvalidPollStateException(currentStatus.value, "clear");

        if (currentStatus == POLL_STATUS.RELEASED) {
            clearChoices();
            currentStatus = POLL_STATUS.CREATED;
        } else {
            clearChoices();
        }
    }

    public synchronized static void closePoll() throws AssignmentException {
        checkPollState(POLL_STATUS.RELEASED, "close");
        currentStatus = POLL_STATUS.CLOSED;
    }

    public synchronized static void runPoll() throws AssignmentException {
        checkPollState(POLL_STATUS.CREATED, "run");
        currentStatus = POLL_STATUS.RUNNING;
    }

    public synchronized static void releasePoll() throws AssignmentException {
        pollReleasedTimestamp = System.nanoTime();

        checkPollState(POLL_STATUS.RUNNING, "release");

        currentStatus = POLL_STATUS.RELEASED;
    }

    public synchronized static void unreleasePoll() throws AssignmentException {
        checkPollState(POLL_STATUS.RELEASED, "unrelease");

        currentStatus = POLL_STATUS.RUNNING;
    }

    public static Map<String, Object> getState() {
        Map<String, Object> mapToReturn = new HashMap<>();

        if (pollInstance != null) {
            mapToReturn.put("choices", pollInstance.getChoicesList());
            mapToReturn.put("question", pollInstance.getQuestionText());
            mapToReturn.put("title", pollInstance.getPollTitle());
        }

        mapToReturn.put("state", currentStatus.value);

        return mapToReturn;
    }

    public synchronized static void vote(HttpSession httpSession, String choice) throws AssignmentException {
        if (choice.isBlank() || choice.isEmpty() || !PollManager.validateChoice(choice))
            throw new InvalidChoiceException();

        checkPollState(POLL_STATUS.RUNNING, "vote");

        if (submittedVotes.containsKey(httpSession.getId()))
            changeVote(httpSession, choice);
        else
            voteCount.put(choice, voteCount.get(choice) + 1);

        submittedVotes.put(httpSession.getId(), choice);
        SessionManager.vote(httpSession, choice);
    }

    public static Map<String, String> getPollResults() {
        Map<String, String> toReturn = new HashMap<>();

        synchronized (voteCount) {

            if (pollInstance == null) {
                return toReturn;
            }

            pollInstance.getChoicesList().stream().sequential().forEach(
                    item -> {
                        toReturn.put(item, voteCount.get(item).toString());
                    }
            );

        }

        return toReturn;
    }

    public synchronized static void downloadPollDetails(PrintWriter output, String fileName) throws PollIsNotReleasedException {
        if (currentStatus == POLL_STATUS.RELEASED) {
            JSONObject detailsJson = new JSONObject(getState());
            detailsJson.put("votes", getPollResults());
            output.print(detailsJson);
            return;
        }

        throw new PollIsNotReleasedException();
    }

    public static Optional<String> getPollTitle() {
        return Optional.of(pollInstance.getPollTitle());
    }


    public synchronized static boolean validateChoice(String choice) throws InvalidPollStateException {
        if (pollInstance == null)
            throw new InvalidPollStateException("none", "vote");
        return pollInstance.getChoicesList().contains(choice);
    }

    public static long getPollReleasedTimestamp() {
        return pollReleasedTimestamp;
    }

    private synchronized static void clearChoices() {
        submittedVotes.clear();
        voteCount.clear();
    }

    private static void checkPollState(POLL_STATUS wantedStatus, String triedAction) throws AssignmentException{
        if(currentStatus != wantedStatus || pollInstance == null)
            throw new InvalidPollStateException(currentStatus.value, triedAction);
    }

    private static void changeVote(HttpSession httpSession, String choice) {
        String oldChoice = httpSession.getAttribute("choice").toString();
        voteCount.put(oldChoice, voteCount.get(oldChoice) - 1);
        voteCount.put(choice, voteCount.get(choice) + 1);
    }

    private static void addChoices(List<String> choices) {
        synchronized (voteCount) {
            choices.forEach(item -> {
                voteCount.put(item, 0);
            });
        }

    }

    private static void initAndCreatePoll(String name, String question, List<String> choices) {
        pollInstance = new Poll(name, question, choices);
        currentStatus = POLL_STATUS.CREATED;
        addChoices(choices);
    }

}
