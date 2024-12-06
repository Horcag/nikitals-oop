package ru.ssau.tk.nikitals.oop.core.ports;

import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionFilter;

import java.util.List;

/**
 * Поиск табулированных функций.
 */
public interface TabulatedFunctionSearch {
    /**
     * Поиск табулированных функций по фильтру.
     * @param filter Фильтр.
     * @return Список табулированных функций.
     */
    List<TabulatedFunctionEntity> search(TabulatedFunctionFilter filter);
}
