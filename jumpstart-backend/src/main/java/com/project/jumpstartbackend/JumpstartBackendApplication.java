package com.project.jumpstartbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.project.jumpstartbackend.config.AppProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class JumpstartBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumpstartBackendApplication.class, args);
		System.out.println("==== Jumpstart app backend is Running! ====");
	}

}
