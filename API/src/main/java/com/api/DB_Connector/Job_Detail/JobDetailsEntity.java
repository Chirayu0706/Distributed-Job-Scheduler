package com.api.DB_Connector.Job_Detail;

import jakarta.persistence.*;

@Entity
@Table(name = "job_details")
public class JobDetailsEntity {

    @Id
    @Column(name = "job_name", nullable = false, unique = true)
    private String jobName;

    @Column(name = "job_cron")
    private String jobCron;

    @Column(name = "job_work")
    private String jobWork;

    @Column(name = "job_status")
    private String jobStatus;

    @Column(name = "job_dependency")
    private String jobDependency;

    // Constructors
    public JobDetailsEntity() {}

    public JobDetailsEntity(String jobName, String jobCron, String jobWork, String jobStatus, String jobDependency) {
        this.jobName = jobName;
        this.jobCron = jobCron;
        this.jobWork = jobWork;
        this.jobStatus = jobStatus;
        this.jobDependency = jobDependency;
    }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getJobCron() { return jobCron; }
    public void setJobCron(String jobCron) { this.jobCron = jobCron; }

    public String getJobWork() { return jobWork; }
    public void setJobWork(String jobWork) { this.jobWork = jobWork; }

    public String getJobStatus() { return jobStatus; }
    public void setJobStatus(String jobStatus) { this.jobStatus = jobStatus; }

    public String getJobDependency() { return jobDependency; }
    public void setJobDependency(String jobDependency) { this.jobDependency = jobDependency; }
}