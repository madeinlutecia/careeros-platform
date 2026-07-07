package com.careeros.diary.infrastructure;

import com.careeros.diary.domain.DiaryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.UUID;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, UUID> {

    Page<DiaryEntry> findByUserIdOrderByEntryDateDesc(UUID userId, Pageable pageable);

    @Query("""
            SELECT d FROM DiaryEntry d
            WHERE d.userId = :userId
            AND (LOWER(d.whatIDid) LIKE LOWER(CONCAT('%', :query, '%'))
                 OR LOWER(d.problemsSolved) LIKE LOWER(CONCAT('%', :query, '%'))
                 OR LOWER(d.lessonsLearned) LIKE LOWER(CONCAT('%', :query, '%')))
            ORDER BY d.entryDate DESC
            """)
    Page<DiaryEntry> search(UUID userId, String query, Pageable pageable);

    Page<DiaryEntry> findByUserIdAndEntryDateBetweenOrderByEntryDateDesc(
            UUID userId, LocalDate from, LocalDate to, Pageable pageable);
}
