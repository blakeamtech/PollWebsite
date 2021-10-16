package Requests;

import Responses.Response;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

public interface Request extends Callable<Response> {

    Response call();

}
