package com.example.model.response.iss;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public record IssPosition(String longitude, String latitude ) {
}