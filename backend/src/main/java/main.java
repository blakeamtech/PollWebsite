import Polls.Poll;
import Util.StringHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.ArrayList;

public class main {
    public static void main(String [] args){
        JSONObject pollJson = new JSONObject();
        pollJson.put("name", "test");
        pollJson.put("question", "testQ");
        pollJson.put("choices", new ArrayList<>());
        pollJson.put("userId", StringHelper.randomPin());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Poll poll = mapper.readValue(pollJson.toString(), Poll.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
