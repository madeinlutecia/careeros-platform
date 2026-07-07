package com.careeros.profile.application;

import com.careeros.profile.api.ProfileResponse;
import com.careeros.profile.api.ProfileUpsertRequest;
import com.careeros.profile.domain.MasterProfile;
import com.careeros.profile.infrastructure.MasterProfileRepository;
import com.careeros.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MasterProfileRepository repository;

    @Transactional(readOnly = true)
    public ProfileResponse getByUserId(UUID userId) {
        return repository.findByUserId(userId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", userId.toString()));
    }

    @Transactional
    public ProfileResponse upsert(UUID userId, ProfileUpsertRequest request) {
        MasterProfile profile = repository.findByUserId(userId).orElseGet(MasterProfile::new);
        profile.setUserId(userId);
        profile.setFullName(request.fullName());
        profile.setHeadline(request.headline());
        profile.setCurrentRole(request.currentRole());
        profile.setAbout(request.about());
        profile.setGithubUrl(request.githubUrl());
        profile.setLinkedinUrl(request.linkedinUrl());
        profile.setPortfolioUrl(request.portfolioUrl());
        if (request.skills() != null) profile.setSkills(request.skills());
        if (request.languages() != null) profile.setLanguages(request.languages());
        return toResponse(repository.save(profile));
    }

    private ProfileResponse toResponse(MasterProfile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getFullName(),
                profile.getHeadline(),
                profile.getCurrentRole(),
                profile.getAbout(),
                profile.getGithubUrl(),
                profile.getLinkedinUrl(),
                profile.getPortfolioUrl(),
                profile.getSkills(),
                profile.getLanguages(),
                profile.getExperiences().stream()
                        .map(e -> new ProfileResponse.ExperienceDto(
                                e.getId(), e.getCompany(), e.getRole(), e.getStartDate(),
                                e.getEndDate(), e.isCurrent(), e.getDescription(), e.getTechnologies()))
                        .toList(),
                profile.getEducations().stream()
                        .map(e -> new ProfileResponse.EducationDto(
                                e.getId(), e.getInstitution(), e.getDegree(), e.getFieldOfStudy(),
                                e.getStartDate(), e.getEndDate()))
                        .toList(),
                profile.getCertifications().stream()
                        .map(c -> new ProfileResponse.CertificationDto(
                                c.getId(), c.getName(), c.getIssuer(), c.getIssuedAt(), c.getCredentialUrl()))
                        .toList()
        );
    }
}
