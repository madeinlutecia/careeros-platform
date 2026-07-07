package com.careeros.profile.domain;

import com.careeros.shared.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "profile_certifications")
@Getter
@Setter
public class Certification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private MasterProfile profile;

    @Column(nullable = false)
    private String name;

    private String issuer;
    private LocalDate issuedAt;
    private String credentialUrl;
}
