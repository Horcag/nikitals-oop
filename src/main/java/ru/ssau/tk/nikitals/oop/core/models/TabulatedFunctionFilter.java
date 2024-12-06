package ru.ssau.tk.nikitals.oop.core.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TabulatedFunctionFilter {
    private String name;
    private String factoryType;
    private boolean sortByName;
}
