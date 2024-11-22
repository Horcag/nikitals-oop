package ru.ssau.tk.nikitals.oop.core.ports;


import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для работы с функциями.
 * @param <T> Тип функции.
 */
public interface BaseFunctionRepository<T> {
    /**
     * Сохранение функции.
     * @param name Функция.
     * @return Сохраненная функция.
     */
    T save(T name);
    /**
     * Поиск функции по ID.
     * @param id ID функции.
     * @return Функция с указанным ID.
     */
    Optional<T> findById(Long id);

    /**
     * Поиск всех функций.
     * @return Список всех функций.
     */
    List<T> findAll();

    /**
     * Удаление функции по ID.
     * @param id ID функции.
     */
    void deleteById(Long id);

}
