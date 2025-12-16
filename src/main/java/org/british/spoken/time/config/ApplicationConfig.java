package org.british.spoken.time.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Configuration
public class ApplicationConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in, StandardCharsets.UTF_8);
    }
}