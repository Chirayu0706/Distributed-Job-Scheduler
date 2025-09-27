package com.api.Job_APIs.Get_Job_Detail;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.DB_Connector.Job_Detail.JobDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class GetJobDetailController {
    private final JobDetailsService service;

    public GetJobDetailController(JobDetailsService service) {
        this.service = service;
    }

    // GET /api/getJobDetail?jobName=Database%20Backup
    @GetMapping("/getJobDetail")
    public Optional getJobDetail(@RequestParam("jobName") String jobName) {
        return service.getJobByName(jobName);
    }
}
