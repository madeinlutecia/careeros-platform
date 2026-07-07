package com.careeros.jobs.application;

import com.careeros.jobs.domain.JobPosting;
import com.careeros.jobs.domain.JobSource;

import java.util.List;

/**
 * Pluggable connector interface for public job boards.
 * Implementations must respect platform Terms of Service.
 */
public interface JobConnector {

    JobSource source();

    List<JobPosting> fetchPublicJobs(JobSearchCriteria criteria);
}
