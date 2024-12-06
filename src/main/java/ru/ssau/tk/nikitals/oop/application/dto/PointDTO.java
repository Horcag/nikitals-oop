package ru.ssau.tk.nikitals.oop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointDTO {
    private Long id;
    private double x;
    private double y;
    private Long functionId;
}
