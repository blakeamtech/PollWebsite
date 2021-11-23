package Requests.objects;

import Exceptions.InvalidPollStateException;
import Polls.Poll;
import Responses.Response;
import Users.PollManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();
    private Poll poll;

    public UpdateRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * Implementation of the Update request. Updates the poll.
     * @return Response object containing body and status request.
     *
     */
    @Override
    public Response call() {
        try{
            poll = mapper.readValue(this.getRequest().getReader(), Poll.class);
            PollManager.updatePoll(poll.getPollTitle(), poll.getQuestionText(), poll.getChoicesList(), poll.getPollId());

            return new Response().ok();
        } catch (IOException | InvalidPollStateException | SQLException | ClassNotFoundException e) {
            return new Response().badRequest().exceptionBody(e);
        }

    }
}
