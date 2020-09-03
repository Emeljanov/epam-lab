package app.by.epamlab.gsu.controllers;

import app.by.epamlab.gsu.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController extends HttpServlet {

    protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void forwardError(String url, String errorMessage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR, errorMessage);
        forward(url, request, response);
    }
}
