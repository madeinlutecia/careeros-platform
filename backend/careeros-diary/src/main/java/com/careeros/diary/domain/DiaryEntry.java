package com.careeros.diary.domain;

import com.careeros.shared.domain.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "career_diary_entries", indexes = {
        @Index(name = "idx_diary_user_date", columnList = "userId, entryDate")
})
@Getter
@Setter
public class DiaryEntry extends TenantEntity {

    @Column(nullable = false)
    private LocalDate entryDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String whatIDid;

    @Column(columnDefinition = "TEXT")
    private String problemsSolved;

    @ElementCollection
    @CollectionTable(name = "diary_technologies", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "technology")
    private List<String> technologies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "diary_people", joinColumns = @JoinColumn(name = "entry_id"))
    @Column(name = "person")
    private List<String> peopleInvolved = new ArrayList<>();

    private Integer timeSpentMinutes;

    @Column(columnDefinition = "TEXT")
    private String lessonsLearned;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaryEntryStatus status = DiaryEntryStatus.RAW;
}
