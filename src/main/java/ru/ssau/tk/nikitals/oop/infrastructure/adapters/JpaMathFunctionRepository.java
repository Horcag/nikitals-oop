package ru.ssau.tk.nikitals.oop.infrastructure.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.nikitals.oop.core.models.MathFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.MathFunctionRepository;

public interface JpaMathFunctionRepository extends JpaRepository<MathFunctionEntity, Long>, MathFunctionRepository {
}
