package com.pailsom.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchProcessingApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplicationBuilder()
				.sources(BatchProcessingApplication.class)
				.web(WebApplicationType.NONE)
				.build();

		ConfigurableApplicationContext applicationContext = application.run(args);
		int exitCode = SpringApplication.exit(applicationContext);
		System.exit(exitCode);
	}

}
