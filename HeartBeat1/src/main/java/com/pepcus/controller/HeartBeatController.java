package com.pepcus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pepcus.service.HeartBeatService;

@RestController
public class HeartBeatController {
  private final HeartBeatService heartBeatService;

  @Autowired
  public HeartBeatController(HeartBeatService heartBeatService) {
    super();
    this.heartBeatService = heartBeatService;
  }
  @GetMapping("/log")
  @Scheduled(cron ="*/10 * * * * *")
 public String getData() {
   return heartBeatService.getApiEmplyoee();
  }
}
