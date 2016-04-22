package mk.gameIt.service.impl;

import mk.gameIt.domain.User;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Created by Stefan on 16.04.2016.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * System default email address that sends the e-mails.
     */
    private String from;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper

        //TODO: FINISH MAIL BELOW
        // final String from = "EMAIL TUKA";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }


    @Async
    public void sendPasswordResetMail(User user, String baseUrl) {
       log.debug("Sending password reset e-mail to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", baseUrl);
        String content = springTemplateEngine.process("passwordResetEmail", context);
        //FINISH TRANSLATE FOR MAIl
        //String subject = messageSource.getMessage("email.reset.title", null, locale);
        String subject = "dadsa";
        sendEmail(user.getEmail(), subject, content, false, true);
    }

}
