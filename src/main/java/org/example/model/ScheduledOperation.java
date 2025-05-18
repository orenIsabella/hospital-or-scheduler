package org.example.model;

import java.time.LocalDateTime;

public class ScheduledOperation {
    private String doctorName;
    private DoctorType doctorType;
    private int roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduledOperation() {}

    public ScheduledOperation(String doctorName, DoctorType doctorType, int roomId, LocalDateTime startTime, LocalDateTime endTime) {
        this.doctorName = doctorName;
        this.doctorType = doctorType;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
