package com.api.DB_Connector.Job_Detail;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobDetailsService {

    private final JobDetailsRepository repository;

    public JobDetailsService(JobDetailsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public JobDetailsEntity addJob(JobDetailsEntity job) {
        return repository.save(job);
    }

    @Transactional(readOnly = true)
    public Optional getJobByName(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Iterable<JobDetailsEntity> getAllJobs() {
        return repository.findAll();
    }

    @Transactional
    public void deleteJobByName(String id) {
        repository.deleteById(id);
    }
}

