package com.careeros.ai.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AiGatewayService {

    private final WebClient.Builder webClientBuilder;

    @Value("${careeros.ai.python-service-url}")
    private String pythonServiceUrl;

    public Map<String, Object> invokeAgent(String agent, UUID userId, Map<String, Object> payload) {
        return webClientBuilder.build()
                .post()
                .uri(pythonServiceUrl + "/api/v1/agents/" + agent)
                .bodyValue(Map.of("userId", userId.toString(), "payload", payload))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
