package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error extends ApiResponse{
    private String type;
    private int status;
    private String message;
}
