package com.careeros.profile.infrastructure;

import com.careeros.profile.domain.MasterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MasterProfileRepository extends JpaRepository<MasterProfile, UUID> {
    Optional<MasterProfile> findByUserId(UUID userId);
}
