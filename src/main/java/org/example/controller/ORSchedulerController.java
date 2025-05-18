package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.DoctorRequest;
import org.example.model.ScheduledOperation;
import org.example.service.SchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduler")
public class ORSchedulerController {

    private final SchedulerService schedulerService;

    public ORSchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/request")
    public ResponseEntity<Object> requestOperation(@Valid @RequestBody DoctorRequest request) {
        Object result = schedulerService.requestSlot(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/operations")
    public ResponseEntity<List<ScheduledOperation>> getAllOperations() {
        return ResponseEntity.ok(schedulerService.getAllScheduledOperations());
    }

    //created for testing purposes, decided to keep it in case you'd like to see the waiting queue
    @GetMapping("/waiting-queue")
    public ResponseEntity<List<DoctorRequest>> getWaitingQueue() {
        return ResponseEntity.ok(schedulerService.getWaitingQueue());
    }

}
