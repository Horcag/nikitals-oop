package ru.ssau.tk.nikitals.oop.infrastructure.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;

/**
 * Спецификации для табулированных функций
 */
public class TabulatedFunctionSpecifications {

    /**
     * Фильтр по имени
     * @param name - имя
     * @return спецификация
     */
    public static Specification<TabulatedFunctionEntity> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    /**
     * Фильтр по типу
     * @param type - тип
     * @return спецификация
     */
    public static Specification<TabulatedFunctionEntity> hasType(String type) {
        return (root, query, cb) -> cb.equal(root.get("factoryType"), type);
    }

    /**
     * Сортировка по имени
     * @return спецификация
     */
    public static Specification<TabulatedFunctionEntity> sortedByName() {
        return (root, query, cb) -> {
            query.orderBy(cb.asc(root.get("name")));
            return null;
        };
    }
}
