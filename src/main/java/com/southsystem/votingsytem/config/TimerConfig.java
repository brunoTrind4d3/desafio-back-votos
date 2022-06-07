package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.service.ExpiredSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerConfig {

    @Autowired
    ExpiredSessionsService service;

    @Scheduled(fixedRate = 5000)
    public void onInterval() {
        this.service.execute();
    }
}
