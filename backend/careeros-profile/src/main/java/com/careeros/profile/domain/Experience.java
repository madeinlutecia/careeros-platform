package com.careeros.profile.domain;

import com.careeros.shared.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "profile_experiences")
@Getter
@Setter
public class Experience extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private MasterProfile profile;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String role;

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean current;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "experience_technologies", joinColumns = @JoinColumn(name = "experience_id"))
    @Column(name = "technology")
    private java.util.List<String> technologies = new java.util.ArrayList<>();
}
