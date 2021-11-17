package Requests;

import Exceptions.AssignmentException;
import Polls.Poll;
import Responses.Response;
import Users.PollManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * This class implements the AbstractRequest abstract class, which requires the use
 * of the super constructor.
 */
public class CreateRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();
    private Poll poll;

    CreateRequest(HttpServletRequest request){
        super(request);

    };

    public Optional<Poll> getPoll(){
        return Optional.of(poll);
    }


    /**
     * Implementation of the Create request. Creates the poll
     * @return Response object containing body and status request.
     */
    @Override
    public Response call() {
        try {
            poll = mapper.readValue(this.getRequest().getReader(), Poll.class);

            PollManager.createPoll(poll.getPollTitle(), poll.getQuestionText(), poll.getChoicesList());

            return new Response().ok().body(new JSONObject("{id:" + PollManager.getPollId() + "}"));
        } catch (IOException | AssignmentException e) {
            return new Response().badRequest().exceptionBody(e);
        }
    }
}
