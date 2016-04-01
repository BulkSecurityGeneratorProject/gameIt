package mk.gameIt.authentication;

import mk.gameIt.domain.Provider;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stefan on 01.04.2016.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        User user = userRepository.findOneByUsername(httpServletRequest.getParameter("username"));
        if(user != null && user.provider != null && user.provider != Provider.LOCAL){
            httpServletResponse.sendRedirect(user.provider.getLoginUrl());
        }
    }
}
