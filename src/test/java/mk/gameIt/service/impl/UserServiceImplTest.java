package mk.gameIt.service.impl;

import mk.gameIt.GameItApplication;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;
import mk.gameIt.web.dto.UserObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Stefan on 16.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameItApplication.class)
@WebAppConfiguration
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @Test
    public void createNewUser() throws IOException, SQLException {
        User user = userRepository.findOneByUsername("Peshou");
        if(user != null){
            userRepository.delete(user.getUserId());
        }
       UserObject userObject = new UserObject();
        userObject.setUsername("Peshou");
        userObject.setEmail("stefan.pesik@gmail.com");
        userObject.setFirstName("Stefan");
        userObject.setLastName("Peshikj");
        userObject.setPassword("pesho123");
        User newUser = userService.createNewUser(userObject);
        assertNotNull(newUser);
    }
    @Test
    public void updateUser() {
    assertTrue(true);
    }


}