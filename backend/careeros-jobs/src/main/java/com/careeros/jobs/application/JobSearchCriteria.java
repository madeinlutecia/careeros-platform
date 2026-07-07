package com.careeros.jobs.application;

import com.careeros.jobs.domain.SeniorityLevel;
import com.careeros.jobs.domain.WorkModel;

import java.util.List;

public record JobSearchCriteria(
        String keywords,
        String location,
        SeniorityLevel seniority,
        WorkModel workModel,
        List<String> technologies,
        int maxResults
) {}
