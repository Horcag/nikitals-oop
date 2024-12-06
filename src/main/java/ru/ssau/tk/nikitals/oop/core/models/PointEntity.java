package ru.ssau.tk.nikitals.oop.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "points")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id", nullable = false)
    @JsonBackReference
    private TabulatedFunctionEntity function;
}
