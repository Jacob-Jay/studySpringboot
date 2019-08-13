package com.jq.spb2;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spb2Application {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Spb2Application.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.setAdditionalProfiles("redisPro");
		application.run(args);
	}

}
