package com.careeros.jobs.domain;

import com.careeros.shared.domain.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_postings", indexes = {
        @Index(name = "idx_jobs_user_status", columnList = "userId, status"),
        @Index(name = "idx_jobs_external", columnList = "source, externalId", unique = true)
})
@Getter
@Setter
public class JobPosting extends TenantEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobSource source;

    private String externalId;
    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private SeniorityLevel seniority;

    @Enumerated(EnumType.STRING)
    private WorkModel workModel;

    private String location;
    private String language;

    @ElementCollection
    @CollectionTable(name = "job_technologies", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "technology")
    private List<String> technologies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "job_benefits", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "benefit")
    private List<String> benefits = new ArrayList<>();

    private BigDecimal compatibilityScore;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.DISCOVERED;

    private Instant discoveredAt = Instant.now();
    private Instant appliedAt;
}
