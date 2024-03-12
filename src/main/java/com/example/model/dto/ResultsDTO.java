package com.example.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultsDTO extends ApiResponse {
    List<PlaceDTO> results;
}
