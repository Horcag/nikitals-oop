package ru.ssau.tk.nikitals.oop.core.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tabulated_function_entity")
public class TabulatedFunctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name; // Название функции.

    @Lob
    @Column(nullable = false)
    private String serializedFunction; // Сериализованное представление функции

    @Lob
    private String serializedResult; // Сериализованный результат (если применимо)
}
