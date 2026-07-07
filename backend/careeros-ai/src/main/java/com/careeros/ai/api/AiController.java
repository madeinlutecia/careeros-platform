package com.careeros.ai.api;

import com.careeros.ai.application.AiGatewayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Tag(name = "IA", description = "Gateway para agentes especializados")
public class AiController {

    private final AiGatewayService aiGatewayService;

    @PostMapping("/agents/{agent}")
    @Operation(summary = "Invocar agente especializado (perfil, currículo, vagas, ATS, mentor...)")
    public Map<String, Object> invokeAgent(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable String agent,
                                           @RequestBody Map<String, Object> payload) {
        return aiGatewayService.invokeAgent(agent, UUID.fromString(jwt.getSubject()), payload);
    }
}
