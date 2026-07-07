package com.careeros.diary.api;

import com.careeros.diary.application.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
@Tag(name = "Diário de Carreira", description = "Registro diário de atividades profissionais")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar entrada no diário de carreira")
    public DiaryEntryResponse create(@AuthenticationPrincipal Jwt jwt,
                                     @Valid @RequestBody DiaryEntryRequest request) {
        return diaryService.create(UUID.fromString(jwt.getSubject()), request);
    }

    @GetMapping
    @Operation(summary = "Listar entradas do diário")
    public Page<DiaryEntryResponse> list(@AuthenticationPrincipal Jwt jwt, Pageable pageable) {
        return diaryService.list(UUID.fromString(jwt.getSubject()), pageable);
    }

    @GetMapping("/search")
    @Operation(summary = "Pesquisar atividades registradas meses atrás")
    public Page<DiaryEntryResponse> search(@AuthenticationPrincipal Jwt jwt,
                                           @RequestParam String q,
                                           Pageable pageable) {
        return diaryService.search(UUID.fromString(jwt.getSubject()), q, pageable);
    }

    @GetMapping("/period")
    @Operation(summary = "Listar entradas por período")
    public Page<DiaryEntryResponse> byPeriod(@AuthenticationPrincipal Jwt jwt,
                                             @RequestParam LocalDate from,
                                             @RequestParam LocalDate to,
                                             Pageable pageable) {
        return diaryService.listByPeriod(UUID.fromString(jwt.getSubject()), from, to, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter entrada específica")
    public DiaryEntryResponse getById(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        return diaryService.getById(UUID.fromString(jwt.getSubject()), id);
    }
}
