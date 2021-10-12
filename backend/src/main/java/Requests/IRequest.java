package Requests;

import Responses.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

public interface IRequest extends Callable<Response> {

    Response call();

}
