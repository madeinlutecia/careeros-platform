package com.careeros.profile.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String fullName,
        String headline,
        String currentRole,
        String about,
        String githubUrl,
        String linkedinUrl,
        String portfolioUrl,
        List<String> skills,
        List<String> languages,
        List<ExperienceDto> experiences,
        List<EducationDto> educations,
        List<CertificationDto> certifications
) {
    public record ExperienceDto(UUID id, String company, String role, LocalDate startDate,
                                LocalDate endDate, boolean current, String description,
                                List<String> technologies) {}

    public record EducationDto(UUID id, String institution, String degree, String fieldOfStudy,
                               LocalDate startDate, LocalDate endDate) {}

    public record CertificationDto(UUID id, String name, String issuer, LocalDate issuedAt,
                                   String credentialUrl) {}
}
