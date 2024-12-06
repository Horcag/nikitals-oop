package ru.ssau.tk.nikitals.oop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionFileDTO {
    TabulatedFunctionDTO tabulatedFunction;
    String format;
}
