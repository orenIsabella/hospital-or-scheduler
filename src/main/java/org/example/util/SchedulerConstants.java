package org.example.util;

import org.example.model.Equipment;
import org.example.model.OR;

import java.time.LocalTime;
import java.util.List;

public class SchedulerConstants {
    public static final LocalTime WORK_START = LocalTime.of(10, 0);
    public static final LocalTime WORK_END = LocalTime.of(18, 0);
    public static final int MAX_DAYS_AHEAD = 7;

    public static final int HEART_SURGERY_DURATION_HOURS = 3;
    public static final int BRAIN_SURGERY_WITH_CT_HOURS = 2;
    public static final int BRAIN_SURGERY_WITHOUT_CT_HOURS = 3;

    public static final String MESSAGE = "message";
    public static final String POSITION_IN_QUEUE = "positionInQueue";
    public static final String QUEUE_MESSAGE = "No available slot in the coming week. You've been added to the queue.";

    public static final List<OR> DEFAULT_OPERATING_ROOMS = List.of(
            new OR(1, List.of(Equipment.MRI, Equipment.CT, Equipment.ECG)),
            new OR(2, List.of(Equipment.CT, Equipment.MRI)),
            new OR(3, List.of(Equipment.CT, Equipment.MRI)),
            new OR(4, List.of(Equipment.MRI, Equipment.ECG)),
            new OR(5, List.of(Equipment.MRI, Equipment.ECG))
    );
}
