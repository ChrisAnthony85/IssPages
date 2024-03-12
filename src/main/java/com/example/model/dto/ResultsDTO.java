package com.example.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.util.List;

@Data
@RegisterForReflection
public class ResultsDTO extends ApiResponse {
    List<PlaceDTO> results;
}
