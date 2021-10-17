package Util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private SessionManager(){};

    private final static Map<Integer, HttpSession> sessionMap = new HashMap<>();
    private static final String POLL_MANAGER_PASSWORD = "poll_manager_yolo";
    private static Integer pollManagerHash;

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


    public static boolean isPollManager(HttpSession session){
        if(pollManagerHash == null){
            return false;
        }

        return session.hashCode() == pollManagerHash;
    }


    public synchronized static boolean setPollManagerSession(HttpSession session, String password){
        if(password.equals(POLL_MANAGER_PASSWORD)){
            pollManagerHash = session.hashCode();
            return true;
        }
        return false;
    }
}
