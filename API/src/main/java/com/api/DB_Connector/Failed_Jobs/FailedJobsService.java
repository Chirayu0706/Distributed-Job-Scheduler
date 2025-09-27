package com.api.DB_Connector.Failed_Jobs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FailedJobsService {
    private final FailedJobsRepository repository;

    public FailedJobsService(FailedJobsRepository repository) {
        this.repository = repository;
    }

    public FailedJobsEntity addFailedJob(FailedJobsEntity failedJob) {
        return repository.save(failedJob);
    }
    public Iterable<FailedJobsEntity> getAllFailedJobs() {
        return repository.findAll();
    }
}
