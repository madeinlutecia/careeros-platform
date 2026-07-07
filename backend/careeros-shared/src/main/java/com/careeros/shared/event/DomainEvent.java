package com.careeros.shared.event;

import java.time.Instant;
import java.util.UUID;

public record DomainEvent(
        UUID eventId,
        String type,
        UUID aggregateId,
        UUID userId,
        Instant occurredAt,
        Object payload
) {
    public static DomainEvent of(String type, UUID aggregateId, UUID userId, Object payload) {
        return new DomainEvent(UUID.randomUUID(), type, aggregateId, userId, Instant.now(), payload);
    }
}
