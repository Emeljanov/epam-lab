package app.by.epamlab.gsu.controllers.filter;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.model.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        if (user == null) {
            session.invalidate();
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(Constants.JUMP_INDEX);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
