package Users;

import Exceptions.AssignmentException;
import Exceptions.InvalidPermissionException;
import Exceptions.InvalidPollCreationInput;
import Exceptions.PollAlreadyInSystemException;
import Polls.Poll;
import Requests.CreateRequest;
import Requests.UpdateRequest;
import Util.SessionManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jdi.request.EventRequestManager;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class PollManager {

    public static enum POLL_STATUS{
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

    // key would be unique identifier (sessionId, wtv) and value would be the vote choice
    private static final Map<String, String> submittedVotes = new HashMap<>();
    private static Poll pollInstance;
    private static POLL_STATUS currentStatus;

    public synchronized static void createPoll(CreateRequest givenCreateRequest)
            throws AssignmentException {

        // if the session doesn't belong to a poll manager, we can't create a poll
        if(!SessionManager.isPollManager(session)){
            throw new InvalidPermissionException();
        }

        // if there exists a poll, can't create a new one
        if(pollInstance != null){
            throw new PollAlreadyInSystemException();
        }

        pollInstance = givenCreateRequest.getPoll().orElseThrow(InvalidPollCreationInput::new);
        currentStatus = POLL_STATUS.CREATED;
    }

    public synchronized static void updatePoll(UpdateRequest request){

    }

    public synchronized static void clearPoll(){}

    public synchronized static void closePoll(){}

    public synchronized static void runPoll(){}

    public synchronized static void releasePoll(){}

    public synchronized static void unreleasePoll(){}

    public synchronized static void vote(HttpSession httpSession, String choice){
        submittedVotes.put(httpSession.getId(), choice);
        SessionManager.vote(httpSession, choice);
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
