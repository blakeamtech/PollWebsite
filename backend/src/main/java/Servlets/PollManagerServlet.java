package Servlets;

import Requests.RequestHandler;
import Storage.PostgreSQLJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/results",     //get
                "/details",     //get
                "/vote",        //post or put
                "/release",     //put, since we're updating the status of the poll to released
                "/unrelease",   //put, status update
                "/clear",       //put, status update + choice update
                "/close",       //put, status update
                "/create",      //put, status update
                "/update",      //put... update
                "/run",          //put, update
                "/state"
        }
)
public class PollManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestHandler.handleGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
        RequestHandler.handlePost(req, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        RequestHandler.handlePut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
    }
}
