package ru.ssau.tk.nikitals.oop.infrastructure.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionFilter;
import ru.ssau.tk.nikitals.oop.core.ports.TabulatedFunctionSearch;
import ru.ssau.tk.nikitals.oop.core.ports.repository.TabulatedFunctionRepository;
import ru.ssau.tk.nikitals.oop.infrastructure.specifications.TabulatedFunctionSpecifications;


import java.util.List;

/**
 * Адаптер для поиска табулированных функций.
 */
@Component
@RequiredArgsConstructor
public class TabulatedFunctionSearchAdapter implements TabulatedFunctionSearch {

    private final TabulatedFunctionRepository repository;

    @Override
    public List<TabulatedFunctionEntity> search(TabulatedFunctionFilter filter) {
        Specification<TabulatedFunctionEntity> spec = Specification.where(null);

        if (filter.getName() != null) {
            spec = spec.and(TabulatedFunctionSpecifications.hasName(filter.getName()));
        }

        if (filter.getFactoryType() != null) {
            spec = spec.and(TabulatedFunctionSpecifications.hasType(filter.getFactoryType()));
        }

        if (filter.isSortByName()) {
            spec = spec.and(TabulatedFunctionSpecifications.sortedByName());
        }

        return repository.findAll(spec);
    }
}
