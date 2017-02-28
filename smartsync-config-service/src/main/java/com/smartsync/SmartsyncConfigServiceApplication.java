package com.smartsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SmartsyncConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartsyncConfigServiceApplication.class, args);
	}
}
