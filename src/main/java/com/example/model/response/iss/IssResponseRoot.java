package com.example.model.response.iss;


public record IssResponseRoot(Long timestamp, IssPosition iss_position, String message) {
}