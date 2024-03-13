package com.berkayarslan.AdviceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdviceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdviceServiceApplication.class, args);
	}

}
