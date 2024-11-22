package ru.ssau.tk.nikitals.oop.infrastructure.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.TabulatedFunctionRepository;

public interface JpaTabulatedFunctionRepository extends TabulatedFunctionRepository, JpaRepository<TabulatedFunctionEntity, Long> {
}
