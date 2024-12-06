package ru.ssau.tk.nikitals.oop.core.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tabulated_functions")
public class TabulatedFunctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name; // Название функции.

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PointEntity> points;

    @Column(name = "factory_type", nullable = false)
    private String factoryType; // Тип фабрики, использованной для создания функции

    public void addPoint(PointEntity point) {
        if (points == null) {
            points = new ArrayList<>();
        }
        points.add(point);
        point.setFunction(this);
    }
}
