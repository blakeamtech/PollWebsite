package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CookieManager {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static class Session {

        public String sessionId;
        public String choice;
        public boolean isPollManager;

        public Session(){
            validateCookieObject(this);
        }
    }

    private static boolean hasSession(HttpServletRequest request){
        return request.getCookies().length > 0;
    }

    private static void validateCookieObject(Session readObject){
        if(readObject.sessionId.isBlank() || readObject.sessionId == null)
            readObject.sessionId = UUID.randomUUID().toString();
    }

    public static boolean updateSession(HttpServletRequest request, HttpServletResponse response, String givenChoice) throws JsonProcessingException {
        if(hasSession(request)){
            Cookie cookie = request.getCookies()[0];
            Session readObject = mapper.readValue(cookie.getValue(), Session.class);
            readObject.choice = givenChoice;
            validateCookieObject(readObject);
            cookie.setValue(mapper.writeValueAsString(readObject));
            response.addCookie(cookie);
            return true;
        }

        Session cookieUserObject = new Session();
        cookieUserObject.choice = givenChoice;
        Cookie cookie = new Cookie("user", mapper.writeValueAsString(cookieUserObject));
        response.addCookie(cookie);
        return true;
    }
}
