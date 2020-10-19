package com.pepa.befit.be_fit_be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BeFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeFitApplication.class, args);
	}

	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
		public CommandLineAppStartupRunner() {
			
		}

		@Override
		public void run(String...args) throws Exception {
			System.out.println(System.getenv("SPRING_DATASOURCE_URL"));
			System.out.println(System.getenv("SPRING_DATASOURCE_USERNAME"));
			System.out.println(System.getenv("SPRING_DATASOURCE_PASSWORD"));
		}
	}
}
