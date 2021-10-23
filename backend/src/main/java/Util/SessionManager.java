package Util;

import Exceptions.InvalidPermissionException;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * The session manager manages HttpSessions from the servlet.
 * It stores sessions inside a HashMap.
 * If the Session is new, we store the Participant's choice. If it is old, we update the participant's choice.
 */
public class SessionManager {

    private SessionManager() throws InvalidPermissionException {
        throw new InvalidPermissionException();
    };

    // hash map which holds the httpSessions, using the SessionID as the key
    private final static Map<Integer, HttpSession> sessionMap = new HashMap<>();

    /**
     * Vote method which takes in a Session and a choice.
     * If the session is already stored, we update the choice.
     * If it is not stored, we add the choice and store it.
     * @param session Given httpSession taken from the servlet
     * @param givenChoice given user choice for the poll
     */
    public static void vote(HttpSession session, String givenChoice){
        synchronized (sessionMap){
            if(sessionMap.containsKey(session.hashCode())){
                sessionMap.get(session.hashCode()).setAttribute("choice", givenChoice);
                return;
            }

            session.setAttribute("choice", givenChoice);
            sessionMap.put(session.hashCode(), session);
        }

    }

}
