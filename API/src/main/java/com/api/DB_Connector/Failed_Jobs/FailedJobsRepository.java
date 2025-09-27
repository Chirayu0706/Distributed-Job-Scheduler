package com.api.DB_Connector.Failed_Jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedJobsRepository extends JpaRepository<FailedJobsEntity, Integer> {

    
}
