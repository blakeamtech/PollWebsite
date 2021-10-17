package Requests;

import Exceptions.AssignmentException;
import Exceptions.InvalidPermissionException;
import Exceptions.InvalidSessionException;
import Polls.Poll;
import Responses.Response;
import Users.PollManager;
import Util.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class CreateRequest extends AbstractRequest implements Request {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final HttpServletRequest request;
    private Poll poll;

    CreateRequest(HttpServletRequest request){
        super(request);
        this.request = request;

    };

    public Optional<Poll> getPoll(){
        return Optional.of(poll);
    }

    @Override
    public Response call() {
        try {
            poll = mapper.readValue(request.getReader(), Poll.class);
            HttpSession session = this.getHttpSession().orElseThrow(InvalidSessionException::new);

            if(!SessionManager.isPollManager(session)){
                throw new InvalidPermissionException();
            }

            PollManager.createPoll(poll.getPollTitle(), poll.getQuestionText(), poll.getChoicesList());

            return new Response().ok();
        } catch (IOException | AssignmentException e) {
            return new Response().badRequest();
        }
    }
}
