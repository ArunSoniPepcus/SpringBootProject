package com.pepcus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HeartBeatService {

  private final RestTemplate restTemplate;

  @Autowired
  public HeartBeatService(RestTemplate restTemplate) {
    super();
    this.restTemplate = restTemplate;
  }

  public String getApiEmplyoee() {
    String url = "http://localhost:8081/actuator/health";
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(url, String.class);
    System.out.println(result);
    return result;
  }

}
