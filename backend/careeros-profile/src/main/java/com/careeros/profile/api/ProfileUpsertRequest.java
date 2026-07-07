package com.careeros.profile.api;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProfileUpsertRequest(
        @NotBlank String fullName,
        String headline,
        String currentRole,
        String about,
        String githubUrl,
        String linkedinUrl,
        String portfolioUrl,
        List<String> skills,
        List<String> languages
) {}
