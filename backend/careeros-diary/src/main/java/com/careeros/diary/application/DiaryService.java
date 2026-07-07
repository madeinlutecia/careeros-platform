package com.careeros.diary.application;

import com.careeros.diary.api.DiaryEntryRequest;
import com.careeros.diary.api.DiaryEntryResponse;
import com.careeros.diary.domain.DiaryEntry;
import com.careeros.diary.domain.DiaryEntryStatus;
import com.careeros.diary.infrastructure.DiaryEntryRepository;
import com.careeros.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryEntryRepository repository;

    @Transactional
    public DiaryEntryResponse create(UUID userId, DiaryEntryRequest request) {
        DiaryEntry entry = new DiaryEntry();
        entry.setUserId(userId);
        applyRequest(entry, request);
        return toResponse(repository.save(entry));
    }

    @Transactional(readOnly = true)
    public Page<DiaryEntryResponse> list(UUID userId, Pageable pageable) {
        return repository.findByUserIdOrderByEntryDateDesc(userId, pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<DiaryEntryResponse> search(UUID userId, String query, Pageable pageable) {
        return repository.search(userId, query, pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<DiaryEntryResponse> listByPeriod(UUID userId, LocalDate from, LocalDate to, Pageable pageable) {
        return repository.findByUserIdAndEntryDateBetweenOrderByEntryDateDesc(userId, from, to, pageable)
                .map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public DiaryEntryResponse getById(UUID userId, UUID entryId) {
        return repository.findById(entryId)
                .filter(e -> e.getUserId().equals(userId))
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("DiaryEntry", entryId.toString()));
    }

    private void applyRequest(DiaryEntry entry, DiaryEntryRequest request) {
        entry.setEntryDate(request.entryDate());
        entry.setWhatIDid(request.whatIDid());
        entry.setProblemsSolved(request.problemsSolved());
        entry.setTechnologies(request.technologies() != null ? request.technologies() : entry.getTechnologies());
        entry.setPeopleInvolved(request.peopleInvolved() != null ? request.peopleInvolved() : entry.getPeopleInvolved());
        entry.setTimeSpentMinutes(request.timeSpentMinutes());
        entry.setLessonsLearned(request.lessonsLearned());
    }

    private DiaryEntryResponse toResponse(DiaryEntry entry) {
        return new DiaryEntryResponse(
                entry.getId(),
                entry.getEntryDate(),
                entry.getWhatIDid(),
                entry.getProblemsSolved(),
                entry.getTechnologies(),
                entry.getPeopleInvolved(),
                entry.getTimeSpentMinutes(),
                entry.getLessonsLearned(),
                entry.getStatus().name()
        );
    }
}
