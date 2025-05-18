package org.example.service;

import org.example.model.*;
import org.example.waitingQueue.OperationWaitingQueue;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.example.model.OperationSlot;
import static org.example.util.SchedulerConstants.*;

@Service
public class SchedulerService {

    private final List<OR> operatingRooms = new ArrayList<>();
    private final List<ScheduledOperation> scheduledOperations = Collections.synchronizedList(new ArrayList<>());
    private final OperationWaitingQueue waitingQueue = new OperationWaitingQueue();

    public SchedulerService() {
        // Initializing operating rooms with their equipment, if the equipmnet needs to be changed it can be changed here
        operatingRooms.addAll(DEFAULT_OPERATING_ROOMS);
    }

    public synchronized Object requestSlot(DoctorRequest request) {
        Optional<OperationSlot> slot = findAvailableSlot(request);

        if (slot.isPresent()) {
            ScheduledOperation op = new ScheduledOperation(
                    request.getDoctorName(),
                    request.getDoctorType(),
                    slot.get().getRoomId(),
                    slot.get().getStart(),
                    slot.get().getEnd()
            );
            scheduledOperations.add(op);
            return op;
        } else {
            if (!waitingQueue.contains(request)) {
                waitingQueue.enqueue(request);
            }
            int position = waitingQueue.getPosition(request);
            return Map.of(
                    MESSAGE, QUEUE_MESSAGE,
                    POSITION_IN_QUEUE, position
            );
        }
    }

    //finds the first available slot, if there is no slot available in the next week, it returns an empty Optional
    private Optional<OperationSlot> findAvailableSlot(DoctorRequest request) {
        int duration = getOperationDuration(request);
        Equipment required = getRequiredEquipment(request);

        for (OR room : operatingRooms) {
            if (!room.getEquipment().contains(required)) continue;
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            LocalDateTime datePointer = now.withHour(10).withMinute(0).withSecond(0);
            for (int day = 0; day < MAX_DAYS_AHEAD; day++) {
                for (int hour = WORK_START.getHour(); hour <= WORK_END.getHour() - duration; hour++) {
                    LocalDateTime start = datePointer.withHour(hour);
                    LocalDateTime end = start.plusHours(duration);
                    boolean overlaps = scheduledOperations.stream().anyMatch(op ->
                            op.getRoomId() == room.getId() &&
                                    (start.isBefore(op.getEndTime()) && end.isAfter(op.getStartTime()))
                    );
                    if (!overlaps && start.isAfter(now)) {
                        return Optional.of(new OperationSlot(room.getId(), start, end));
                    }
                }
                datePointer = datePointer.plusDays(1);
            }
        }
        return Optional.empty();
    }

    private int getOperationDuration(DoctorRequest request) {
        return switch (request.getDoctorType()) {
            case HEART -> HEART_SURGERY_DURATION_HOURS;
            case BRAIN -> hasCTSupport(request) ? BRAIN_SURGERY_WITH_CT_HOURS : BRAIN_SURGERY_WITHOUT_CT_HOURS;
        };
    }

    private Equipment getRequiredEquipment(DoctorRequest request) {
        return switch (request.getDoctorType()) {
            case HEART -> Equipment.ECG;
            case BRAIN -> Equipment.MRI;
        };
    }

    //needed for brain operations to take 2 hours instead of 3
    private boolean hasCTSupport(DoctorRequest request) {
        return operatingRooms.stream()
                .filter(or -> or.getEquipment().contains(Equipment.CT))
                .anyMatch(or -> or.getEquipment().containsAll(List.of(Equipment.MRI, Equipment.CT)));
    }

    //for testing purposes
    public List<ScheduledOperation> getAllScheduledOperations() {
        return scheduledOperations;
    }

    public List<DoctorRequest> getWaitingQueue() {
        return waitingQueue.getWaitingQueue();
    }

}