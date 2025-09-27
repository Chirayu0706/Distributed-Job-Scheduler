package com.api.DB_Connector.Failed_Jobs;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "failed_jobs")

public class FailedJobsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fail_id")
    private Integer failId;
    
    @Column(name = "job_name", nullable = false, length = 255)
    private String jobName;
    
    @Column(name = "fail_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime failTime;
    
    @Column(name = "executor", length = 255)
    private String executor;
    
    // If you have a JobDetail entity, you can add this relationship:
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "job_name", referencedColumnName = "job_name", insertable = false, updatable = false)
    // private JobDetail jobDetail;
    
    // Constructors
    public FailedJobsEntity() {
        this.failTime = LocalDateTime.now();
    }
    
    public FailedJobsEntity(String jobName, String executor) {
        this();
        this.jobName = jobName;
        this.executor = executor;
    }
    
    // Getters and Setters
    public Integer getFailId() {
        return failId;
    }
    
    public void setFailId(Integer failId) {
        this.failId = failId;
    }
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public LocalDateTime getFailTime() {
        return failTime;
    }
    
    public void setFailTime(LocalDateTime failTime) {
        this.failTime = failTime;
    }
    
    public String getExecutor() {
        return executor;
    }
    
    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
