package Users;

import Exceptions.AssignmentException;
import Exceptions.PollAlreadyInSystemException;
import Polls.Poll;
import Util.SessionManager;

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

    public synchronized static void createPoll(String name, String question, List<String> choices)
            throws AssignmentException {

        // if there exists a poll, can't create a new one
        if(pollInstance != null){
            throw new PollAlreadyInSystemException();
        }

        pollInstance = new Poll(name, question, choices);
        currentStatus = POLL_STATUS.CREATED;
    }

    public synchronized static boolean updatePoll(){

        return false;
    }

    public synchronized static boolean clearPoll(){

        return false;
    }

    public synchronized static boolean closePoll(){

        return false;
    }

    public synchronized static boolean runPoll(){

        return false;
    }

    public synchronized static boolean releasePoll(){

        return false;
    }

    public synchronized static boolean unreleasePoll(){

        return false;
    }

    public synchronized static void vote(HttpSession httpSession, String choice){
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

    public synchronized static void downloadPollDetails(PrintWriter output, String fileName){

    }



    public synchronized static boolean validateChoice(String choice) {
        return pollInstance.getChoicesList().contains(choice);
    }



}
