package mk.gameIt.service.impl;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.service.MailSender;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Service
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private TemplateEngine textTemplateEngine;

    @Autowired
    private TemplateEngine stringTemplateEngine;

    @Value("${spring.mail.username}")
    private String username;

    @Async
    public void placeOrder(final UserGameOrder userGameOrder) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(userGameOrder.getUser().getEmail());
            message.setFrom(username);
            message.setSubject("Order Confirmation");
            message.setText("Dear " + userGameOrder.getUser().getUsername()
                    + ", thank you for placing order. Your order number is "
                    + userGameOrder.getOrderId(), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendReistrationSuccessEmail(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(user.getEmail());
            message.setFrom(username);
            message.setSubject("Account Registration");
            message.setText("Dear " + user.getUsername()
                    + ", Your account has been created. <br/> Thank you for your registration.<br/> The Game It Team.", true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendNewsLetter(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(user.getEmail());
            message.setFrom(username);
            message.setSubject("Newsletter");
            message.setText("Dear " + user.getUsername()
                    + ", Thank you for receiving this newsletter", true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendOrderEmail(User user, Game game, Long orderId) throws MessagingException {
//        // Prepare the evaluation context
//        final Context ctx = new Context();
//        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
//
//        // Prepare message using a Spring helper
//        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//        final MimeMessageHelper message =
//                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
//        message.setSubject("Order Confirmation");
//        message.setFrom(username);
//        message.setTo(user.getEmail());
//
//        // Create the HTML body using Thymeleaf
//        final String htmlContent = this.htmlTemplateEngine.process("orderCreation.html", ctx);
//        message.setText(htmlContent, true); // true = isHtml
//
//        // Send mail
//        this.mailSender.send(mimeMessage);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(user.getEmail());
            message.setFrom(username);
            message.setSubject("Order Confirmation");
            message.setText("Dear " + user.getUsername()
                    + ", thank you for placing order. <br/> Your order number is "+ orderId, true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}