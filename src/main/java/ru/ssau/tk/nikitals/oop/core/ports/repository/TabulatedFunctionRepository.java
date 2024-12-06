package ru.ssau.tk.nikitals.oop.core.ports.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;

import java.util.List;

/**
 * Репозиторий для работы с табулированными функциями.
 */
public interface TabulatedFunctionRepository extends BaseFunctionRepository<TabulatedFunctionEntity> {

    List<TabulatedFunctionEntity> findByNameContainingIgnoreCase(String name);

    // Поиск по типу
    List<TabulatedFunctionEntity> findByFactoryType(String type);

    List<TabulatedFunctionEntity> findAll(Specification<TabulatedFunctionEntity> spec);
}
