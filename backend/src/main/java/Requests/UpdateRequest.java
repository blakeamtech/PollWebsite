package Requests;

import Exceptions.InvalidPollStateException;
import Polls.Poll;
import Responses.Response;
import Users.PollManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UpdateRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();
    private Poll poll;

    UpdateRequest(HttpServletRequest request){
        super(request);
    };

    @Override
    public Response call() {
        try{
            poll = mapper.readValue(this.getRequest().getReader(), Poll.class);

            PollManager.updatePoll(poll.getPollTitle(), poll.getQuestionText(), poll.getChoicesList());

            return new Response().ok();
        } catch (IOException | InvalidPollStateException e) {
            return new Response().badRequest();
        }

    }
}
