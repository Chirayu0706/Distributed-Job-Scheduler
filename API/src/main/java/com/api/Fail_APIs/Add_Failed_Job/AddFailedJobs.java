package com.api.Fail_APIs.Add_Failed_Job;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Failed_Jobs.FailedJobsEntity;
import com.api.DB_Connector.Failed_Jobs.FailedJobsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class AddFailedJobs {
    private final FailedJobsService service;
    public AddFailedJobs(FailedJobsService service) {
        this.service = service;
    }
    @PostMapping("/addFailedJob")
    public FailedJobsEntity addFailedJob(@RequestBody FailedJobsEntity job) {
        return service.addFailedJob(job);
    }
}
