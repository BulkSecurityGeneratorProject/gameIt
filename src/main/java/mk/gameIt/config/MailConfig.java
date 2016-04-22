package mk.gameIt.config;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Properties;

/**
 * Created by Stefan on 16.04.2016.
 */
@Configuration
public class MailConfig {
    private final Logger log = LoggerFactory.getLogger(MailConfig.class);

    public static final String MAIL_PREFIX = "app.mail.";
    public static final String MAIL_PROPERTIES_PREFIX = "app.mail.properties.";
    public static final String MAIL_USERNAME = "username";
    public static final String MAIL_PASSWORD = "password";
    public static final String MAIL_PORT = "port";
    public static final String MAIL_HOST = "host";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_DEBUG = "mail.debug";

    @Autowired
    private Environment environment;

    private RelaxedPropertyResolver mailConfig;

    private RelaxedPropertyResolver mailPropertiesConfig;

    public void initPropertyResolvers() {
        mailConfig = new RelaxedPropertyResolver(environment, MAIL_PREFIX);
        mailPropertiesConfig = new RelaxedPropertyResolver(environment, MAIL_PROPERTIES_PREFIX);
    }


    @Bean
    @Description("Thymeleaf template resolver serving HTML 5 emails")
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("mails/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
        emailTemplateResolver.setOrder(1);
        return emailTemplateResolver;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        initPropertyResolvers();

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setUsername(mailConfig.getProperty(MAIL_USERNAME));
        javaMailSender.setPassword(mailConfig.getProperty(MAIL_PASSWORD));
        javaMailSender.setPort(mailConfig.getProperty(MAIL_PORT, Integer.class));
        javaMailSender.setHost(mailConfig.getProperty(MAIL_HOST));

        javaMailSender.setJavaMailProperties(loadJavaMailProperties());

        return javaMailSender;
    }

    private Properties loadJavaMailProperties() {
        Properties properties = new Properties();
        properties.setProperty(MAIL_TRANSPORT_PROTOCOL,mailPropertiesConfig.getProperty(MAIL_TRANSPORT_PROTOCOL));
        properties.setProperty(MAIL_SMTP_AUTH, mailPropertiesConfig.getProperty(MAIL_SMTP_AUTH));
        properties.setProperty(MAIL_SMTP_STARTTLS_ENABLE, mailPropertiesConfig.getProperty(MAIL_SMTP_STARTTLS_ENABLE));
        properties.setProperty(MAIL_DEBUG, mailPropertiesConfig.getProperty(MAIL_DEBUG, "false"));

        return properties;
    }


}
