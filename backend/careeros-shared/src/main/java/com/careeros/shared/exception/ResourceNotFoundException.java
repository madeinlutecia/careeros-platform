package com.careeros.shared.exception;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resource, String id) {
        super("RESOURCE_NOT_FOUND", "%s not found: %s".formatted(resource, id));
    }
}
