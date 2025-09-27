package com.api.DB_Connector.Job_Detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailsRepository extends JpaRepository<JobDetailsEntity, String> {
    //Here String is the type of primary key
}