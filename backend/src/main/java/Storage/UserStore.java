package Storage;

import Users.User;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    public static final Map<String, User> userMap = new HashMap<>();

    public static void addUser(User user){
        userMap.put(user.userId, user);
    }

    public static void vote(User user, String pin){
        if(userMap.containsKey(user.userId)){
            userMap.get(user.userId).vote(pin);
            return;
        }
        user.vote(pin);
        userMap.put(user.userId, user);
    }

}
