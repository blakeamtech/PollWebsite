package Interfaces;

import Responses.Response;
import java.util.concurrent.Callable;

public interface Request extends Callable<Response> {

    Response call();

}
