package com.pepcus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
@Configuration
@EnableScheduling
public class HeartBearConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
