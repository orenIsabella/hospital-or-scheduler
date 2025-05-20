package org.example.waitingQueue;

import org.example.model.DoctorRequest;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OperationWaitingQueue {
    private final Queue<DoctorRequest> waitingQueue = new ConcurrentLinkedQueue<>();

    public void enqueue(DoctorRequest request) {
        waitingQueue.add(request);
    }

    public DoctorRequest dequeue() {
        return waitingQueue.poll();
    }

    public int getPosition(DoctorRequest request) {
        int pos = 1; // I've decided to start from 1 and not 0 for "user-friendly position".
        for (DoctorRequest r : waitingQueue) {
            if (r.equals(request)) return pos;
            pos++;
        }
        return -1;
    }

    public void remove(DoctorRequest request) {
        if (request != null) {
            waitingQueue.remove(request);
        }
    }

    public boolean contains(DoctorRequest request) {
        return waitingQueue.contains(request);
    }

    public List<DoctorRequest> getWaitingQueue() {
        return List.copyOf(waitingQueue);
    }

    public boolean isEmpty() {
        return waitingQueue.isEmpty();
    }
}
