package mk.gameIt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameItApplication {
	private final Logger log = LoggerFactory.getLogger(GameItApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameItApplication.class, args);
	}
}
