package com.careeros.diary.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DiaryEntryRequest(
        @NotNull LocalDate entryDate,
        @NotBlank String whatIDid,
        String problemsSolved,
        List<String> technologies,
        List<String> peopleInvolved,
        Integer timeSpentMinutes,
        String lessonsLearned
) {}
