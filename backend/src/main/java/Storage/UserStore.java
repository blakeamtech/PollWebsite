package Storage;

import Users.User;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    public static final Map<String, User> userMap = new HashMap<>();

    public static void addUser(User user){
        userMap.put(user.userId, user);
    }

    public static void addUserPollId(User user, String pollId){
        if(userMap.containsKey(user.userId)){
            userMap.get(user.userId).addPollId(pollId);
            return;
        }
        user.addPollId(pollId);
        userMap.put(user.userId, user);
    }

}
