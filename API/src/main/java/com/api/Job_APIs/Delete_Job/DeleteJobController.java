package com.api.Job_APIs.Delete_Job;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Job_Detail.JobDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class DeleteJobController {
    private final JobDetailsService service;

    public DeleteJobController(JobDetailsService service) {
        this.service = service;
    }

    @PostMapping("/deleteJob/{jobName}")
    public String deleteJob(@PathVariable("jobName") String jobName) {
        service.deleteJobByName(jobName);
        return "Job deleted successfully";
    }
}
