package com.flynow.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages ={ "com.flynow.api.entity"})
@EnableJpaRepositories(basePackages="com.flynow.api.repository")
@EnableAsync
@EnableEurekaClient
public class PlanesSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanesSearchServiceApplication.class, args);
	}

}
