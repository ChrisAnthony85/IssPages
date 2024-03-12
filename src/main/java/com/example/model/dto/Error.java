package com.example.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RegisterForReflection
public class Error extends ApiResponse {
    private String type;
    private int status;
    private String message;
}
