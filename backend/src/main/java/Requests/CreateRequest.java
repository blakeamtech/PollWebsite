package Requests;

import Polls.Poll;
import Responses.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;

public class CreateRequest implements IRequest{

    private static final ObjectMapper mapper = new ObjectMapper();
    private Poll poll;

    CreateRequest(HttpServletRequest request){
        try {
            poll = mapper.readValue(request.getReader(), Poll.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };



    public Optional<Poll> getPoll(){
        return Optional.of(poll);
    }

    @Override
    public Response call() {
        /**
         * Create poll
         */
        return null;
    }
}
