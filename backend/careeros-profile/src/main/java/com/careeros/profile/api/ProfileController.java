package com.careeros.profile.api;

import com.careeros.profile.application.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Tag(name = "Perfil Mestre", description = "Fonte única da verdade da carreira do usuário")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    @Operation(summary = "Obter perfil mestre do usuário autenticado")
    public ProfileResponse getMyProfile(@AuthenticationPrincipal Jwt jwt) {
        return profileService.getByUserId(extractUserId(jwt));
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Criar ou atualizar perfil mestre")
    public ProfileResponse upsertMyProfile(@AuthenticationPrincipal Jwt jwt,
                                           @Valid @RequestBody ProfileUpsertRequest request) {
        return profileService.upsert(extractUserId(jwt), request);
    }

    private UUID extractUserId(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
