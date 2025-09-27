package com.api.Job_APIs.Add_Job;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Job_Detail.JobDetailsEntity;
import com.api.DB_Connector.Job_Detail.JobDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class AddJobController {
    private final JobDetailsService service;

    public AddJobController(JobDetailsService service) {
        this.service = service;
    }

    @PostMapping("/addJob")
    public JobDetailsEntity addJob(@RequestBody JobDetailsEntity job) {
        return service.addJob(job);
    }
}