package com.careeros.diary.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DiaryEntryResponse(
        UUID id,
        LocalDate entryDate,
        String whatIDid,
        String problemsSolved,
        List<String> technologies,
        List<String> peopleInvolved,
        Integer timeSpentMinutes,
        String lessonsLearned,
        String status
) {}
