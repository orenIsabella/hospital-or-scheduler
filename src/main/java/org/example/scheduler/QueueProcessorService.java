package org.example.scheduler;

import org.example.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueProcessorService {

    @Autowired
    private SchedulerService schedulerService;

    @Scheduled(fixedRate = 3600000)
    public void runQueueCheck() {
        System.out.println("Running scheduled queue check...");
        schedulerService.checkQueue();
    }
}
