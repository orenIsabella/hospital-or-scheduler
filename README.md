# 🏥 Operating Room Scheduler

A Spring Boot application that simulates an **Operating Room Scheduler** for hospitals.  
Doctors can request to schedule surgeries, get matched to available ORs, or enter a queue if all rooms are booked.  
Built with **Java 17**, **Spring Boot**, and a strong focus on **concurrency** and **clean API design**.

---

## 🚀 How to Run

### ✅ Prerequisites
- Java 17+
- Maven

### ▶️ Run It

```bash
git clone https://github.com/orenIsabella/hospital-or-scheduler.git
cd or-scheduler
mvn clean install
mvn spring-boot:run
```

Runs at:  
👉 `http://localhost:8080`

---

## 🔌 API Endpoints

### 📥 `POST /api/scheduler/request`
Request the next available surgery slot.

**Request Body:**
```json
{
  "doctorType": "HEART",
  "doctorName": "Dr. Doctor"
}
```

✅ Valid `doctorType` values: `HEART`, `BRAIN`

**Response:**  
🟢 If a slot is available:
```json
{
  "doctorName": "Dr. Doctor",
  "doctorType": "HEART",
  "roomId": 1,
  "startTime": "2025-05-18T10:00:00",
  "endTime": "2025-05-18T13:00:00"
}
```

🟡 If all rooms are full:
```json
{
  "message": "No available slot in the coming week. You've been added to the queue.",
  "positionInQueue": 1
}
```

---

### 📤 `GET /api/scheduler/operations`
Returns all currently scheduled operations.

---

### 📋 `GET /api/scheduler/waiting-queue`
Returns the list of doctors waiting for a slot.

---

## ⚙️ Concurrency Support

> **Requirement:** _"Multiple doctors can communicate with the OR Scheduler at the same time."_

✅ This app is **thread-safe** and handles **concurrent requests** using:
- Synchronized methods
- Thread-safe data structures (`ConcurrentLinkedQueue`, `Collections.synchronizedList`)
- Proper locking to prevent double-booking and race conditions

You can send multiple parallel requests and the system will respond safely and correctly — even under load.

---

## ❗ Note on Lombok

Model classes (like `DoctorRequest`, `OR`, etc.) **could be simplified** using Lombok annotations such as `@Data`, `@AllArgsConstructor`, and `@Getter`.

However, due to persistent issues with annotation processing in IntelliJ during development, the project uses plain Java methods to ensure full compatibility during compilation and fewer environment-specific issues.

You’re welcome to re-enable Lombok if your setup supports it!

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3
- Maven
- Jakarta Validation
- Thread-safe Java collections
