package com.api.Job_APIs.Get_All_Jobs;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Job_Detail.JobDetailsEntity;
import com.api.DB_Connector.Job_Detail.JobDetailsService;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class GetAllJobsController {
        private final JobDetailsService service;

    public GetAllJobsController(JobDetailsService service) {
        this.service = service;
    }
    @GetMapping("/getAllJobs")
    public Iterable<JobDetailsEntity> getAllJobs() {
        return service.getAllJobs();
    }
}
