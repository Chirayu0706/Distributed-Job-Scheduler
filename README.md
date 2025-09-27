Distributed Job Scheduler

A fault-tolerant, distributed job scheduling system built with Spring Boot, RabbitMQ, Postgres, and Docker Compose.
It allows jobs to be dynamically created/updated via APIs and executes them reliably across multiple worker nodes with retries, heartbeats, and failure detection.

✨ Features

Dynamic Scheduling – Create, update, and delete jobs at runtime via REST APIs.

Distributed Execution – Multiple executors compete for jobs through RabbitMQ.

Retries with Backoff – Automatic retries up to 3 times before marking a job as failed.

Heartbeat Monitoring – Detects unresponsive executors (crash, infinite loop, stuck jobs).

Failure Recovery – Re-queues jobs from dead executors automatically.

UI Dashboard – Web-based UI for creating and monitoring jobs.

CORS-enabled APIs – Frontend integration with REST APIs.

🏗️ Architecture Overview

API Service – Handles CRUD on jobs in Postgres.

Scheduler Service – Watches DB in real-time, enqueues jobs, monitors executor heartbeats.

Executor Service(s) – Picks up jobs from MQ, executes them, retries on failure, sends heartbeat.

Message Queues – RabbitMQ for job dispatching + executor monitoring.

Database – Postgres for persistent job storage and execution history.

🖥️ UI Screenshots

<img width="1864" height="777" alt="image" src="https://github.com/user-attachments/assets/38efd7d8-7bd7-440d-9fb5-cc1c33960e53" />

<img width="936" height="849" alt="image" src="https://github.com/user-attachments/assets/efa8833e-2184-4668-8c77-c1634bb18a27" />

<img width="1802" height="787" alt="image" src="https://github.com/user-attachments/assets/a91e96b5-7022-409f-8331-63ebf37aefe5" />

<img width="992" height="861" alt="image" src="https://github.com/user-attachments/assets/91218384-f7ee-4429-8153-8fa6c2671a50" />

⚡ Tech Stack

Java + Spring Boot

RabbitMQ

Postgres

Docker Compose

React (UI)

🚀 Getting Started
# Clone the repo
git clone https://github.com/<your-username>/distributed-job-scheduler.git
cd distributed-job-scheduler

# Start the system
docker-compose up --build


APIs will be available at:

API Service → http://localhost:8080/api/...

UI → http://localhost:3000/

📚 Example API Usage
Create Job
POST /api/jobs
Content-Type: application/json

{
  "cron": "0 */5 * * *",
  "payload": { "url": "http://example.com/notify", "method": "POST" }
}

List Jobs
GET /api/jobs

🧠 What I Learned

Designing a distributed system with heartbeat checks and fault tolerance.

Handling job retries and backoff strategies in executors.

Coordinating multiple services with Docker Compose networking.

Implementing real-time monitoring for reliability.

📌 Future Enhancements

Add leader election for multiple schedulers.

Add job dependencies and priorities.

Enhance UI with job history analytics.

Add Prometheus + Grafana monitoring dashboards.
