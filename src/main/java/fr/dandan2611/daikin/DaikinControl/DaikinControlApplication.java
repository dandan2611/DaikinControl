package fr.dandan2611.daikin.DaikinControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DaikinControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaikinControlApplication.class, args);
	}

}
