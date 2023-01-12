package com.iot.light.blidns.iotproject;

import com.iot.light.blidns.iotproject.mqtt.MqttEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IotProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner run() {
		MqttEngine mqttEngine = new MqttEngine();
		return args -> {
			mqttEngine.receiveMessage();
		};
	}

}
