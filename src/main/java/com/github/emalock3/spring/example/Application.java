package com.github.emalock3.spring.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
  public static void main(String ... args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setWebEnvironment(false);
    app.run(args);
  }
}
