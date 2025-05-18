package org.example.model;

import java.time.LocalDateTime;

public class OperationSlot {
    private final int roomId;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public OperationSlot(int roomId, LocalDateTime start, LocalDateTime end) {
        this.roomId = roomId;
        this.start = start;
        this.end = end;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
