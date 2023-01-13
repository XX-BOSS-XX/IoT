package com.iot.light.blidns.iotproject;

import com.iot.light.blidns.iotproject.entity.*;
import com.iot.light.blidns.iotproject.mqtt.MqttEngine;
import com.iot.light.blidns.iotproject.repository.BlindsRepository;
import com.iot.light.blidns.iotproject.repository.LightRepository;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import com.iot.light.blidns.iotproject.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class IotProjectApplication {
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(IotProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, SensorRepository sensorRepository,
                          LightRepository lightRepository,
                          BlindsRepository blindsRepository,
                          MqttEngine mqtt) {
        MqttEngine mqttEngine = new MqttEngine();
        return args -> {
            Sensor sensor1 = new Sensor(0);
            Sensor sensorMin = new Sensor(0);
            Sensor sensorMax = new Sensor(1000);
            Sensor sensorValue = new Sensor(0);
            sensorRepository.save(sensor1);
            sensorRepository.save(sensorMin);
            sensorRepository.save(sensorMax);
            sensorRepository.save(sensorValue);

            Blinds blinds1 = new Blinds(2362, sensor1);
            Blinds blinds2 = new Blinds(2356, sensor1);
            Blinds blinds3 = new Blinds(2328, sensor1);
            Blinds blinds4 = new Blinds(2307, sensor1);
            blindsRepository.save(blinds1);
            blindsRepository.save(blinds2);
            blindsRepository.save(blinds3);
            blindsRepository.save(blinds4);

            Light light1 = new Light(2053 ,sensor1);
            Light light2 = new Light(2050 ,sensor1);
            lightRepository.save(light1);
            lightRepository.save(light2);

            mqtt.receive("my/test/topic");

            var user = AppUser.builder()
                    .username("admin")
                    .email("admin@tuke.sk")
                    .password(passwordEncoder.encode("qwerty"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(
                    user
            );
        };

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        .maxAge(30)
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**", configuration);
        return url;
    }

}
