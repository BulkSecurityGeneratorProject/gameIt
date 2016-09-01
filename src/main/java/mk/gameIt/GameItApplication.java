package mk.gameIt;

import mk.gameIt.service.UserGameOrderService;
import mk.gameIt.service.impl.UserGameOrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@EnableAsync
@EnableScheduling
public class GameItApplication extends WebMvcAutoConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(GameItApplication.class, args);

	}

}

