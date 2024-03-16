package com.example.KafkaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
	}

}
