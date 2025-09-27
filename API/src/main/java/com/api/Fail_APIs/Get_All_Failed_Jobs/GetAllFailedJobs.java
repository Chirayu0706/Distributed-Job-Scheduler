package com.api.Fail_APIs.Get_All_Failed_Jobs;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Failed_Jobs.FailedJobsEntity;
import com.api.DB_Connector.Failed_Jobs.FailedJobsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class GetAllFailedJobs {
    private final FailedJobsService service;

    public GetAllFailedJobs(FailedJobsService service) {
        this.service = service;
    }
    @GetMapping("/getAllFailedJobs")
    public Iterable<FailedJobsEntity> getAllFailedJobs() {
        return service.getAllFailedJobs();
    }
}
