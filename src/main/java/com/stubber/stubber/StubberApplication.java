package com.stubber.stubber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class StubberApplication implements CommandLineRunner {
	@Autowired
	private WireMockService wireMockService;

	public static void main(String[] args) {
		SpringApplication.run(StubberApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		wireMockService.addMapping();
	}

}
