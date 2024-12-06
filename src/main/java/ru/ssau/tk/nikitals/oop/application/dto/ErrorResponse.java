package ru.ssau.tk.nikitals.oop.application.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String details;
}
