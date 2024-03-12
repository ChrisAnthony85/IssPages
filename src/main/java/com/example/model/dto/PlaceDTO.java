package com.example.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class PlaceDTO {
    private String title;
    private double latitude;
    private double longitude;
    private String country;
}
