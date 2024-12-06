package ru.ssau.tk.nikitals.oop.infrastructure.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.repository.TabulatedFunctionRepository;


/**
 * Репозиторий для работы с табулированными функциями.
 */
public interface JpaTabulatedFunctionRepository extends TabulatedFunctionRepository,
        JpaRepository<TabulatedFunctionEntity, Long>,
        JpaSpecificationExecutor<TabulatedFunctionEntity> {
}
