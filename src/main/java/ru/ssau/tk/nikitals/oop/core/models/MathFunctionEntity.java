package ru.ssau.tk.nikitals.oop.core.models;

import jakarta.persistence.*;
import lombok.*;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MathFunctionEntity implements MathFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название функции

    @Column(nullable = false)
    private String type; // Тип функции (например, SqrFunction)

    @Lob
    private String parameters; // Параметры функции (JSON для гибкости)

    @Override
    public double apply(double x) {
        // Реализация зависит от типа функции.
        throw new UnsupportedOperationException("apply не реализован для MathFunctionEntity");
    }
}