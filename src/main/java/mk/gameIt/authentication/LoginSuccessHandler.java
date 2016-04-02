package mk.gameIt.authentication;

import mk.gameIt.domain.Provider;
import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by Stefan on 01.04.2016.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Provider provider;
    private Role role;

    public LoginSuccessHandler(Provider provider, Role role) {
        this.provider = provider;
        this.role = role;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(authentication);
       // OAuth2Authentication auth = (OAuth2Authentication)authentication;
        //UsernamePasswordAuthenticationToken token= (UsernamePasswordAuthenticationToken)auth.getUserAuthentication();
        // LinkedHashMap<String,String> details = (LinkedHashMap<String, String>) token.getDetails();
        //System.out.println(details.get("name"));
        User user = userRepository.findOneByUsername(authentication.getName());
        if(user == null){
      //      OAuthAccount account = new OAuthAccount();
            user = new User();
            user.setUsername(authentication.getName());
            user.setRole(role);
            user.setProvider(provider);
            userRepository.save(user);
        }
        session.setAttribute("user",user);
        super.onAuthenticationSuccess(request,response,authentication);
    }
}