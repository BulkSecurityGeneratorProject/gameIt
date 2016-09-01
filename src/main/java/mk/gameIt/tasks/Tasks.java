package mk.gameIt.tasks;

import mk.gameIt.domain.User;
import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.service.MailSender;
import mk.gameIt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Component
public class Tasks {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService userService;


    //EVERY DAY FROM 10 - 17, EVERY HALF AN HOUR, 10:30, 11:00, 11:30, 12:00, 12:30 ....
    @Scheduled(cron = "0 0/30 10-17 * * *")
    public void sendPromotionEmail(){
        List<User> userList = userService.findAll();
        for(User user : userList) {
            mailSender.sendNewsLetter(user);
        }
    }
}
