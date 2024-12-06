package ru.ssau.tk.nikitals.oop.infrastructure.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.repository.PointRepository;

/**
 * Репозиторий для работы с точками.
 */
public interface JpaPointRepository extends PointRepository,
        JpaRepository<PointEntity, Long> {
//    boolean existsPointEntityByFunctionAndX(TabulatedFunctionEntity function, double x);
}
