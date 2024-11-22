package ru.ssau.tk.nikitals.oop.core.domain.functions.api;

import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;

/**
 * Интерфейс, представляющий табулированную функцию.
 * <p>Расширяет интерфейс MathFunction и добавляет методы, специфичные для табулированных функций.</p>
 */
public interface TabulatedFunction extends MathFunction, Iterable<Point> {

    /**
     * Возвращает количество табулированных значений.
     *
     * @return количество табулированных значений
     */
    int getCount();

    /**
     * Возвращает значение {@code x} по указанному индексу.
     *
     * @param index индекс значения {@code x}
     * @return значение {@code x} по указанному индексу
     */
    double getX(int index);

    /**
     * Возвращает значение {@code y} по указанному индексу.
     *
     * @param index индекс значения {@code y}
     * @return значение {@code y} по указанному индексу
     */
    double getY(int index);

    /**
     * Устанавливает значение {@code y} по указанному индексу.
     *
     * @param index индекс значения {@code y}
     * @param value значение {@code y}
     */
    void setY(int index, double value);

    /**
     * Возвращает индекс указанного значения {@code x}.
     * <p>
     * Если значение {@code x} не найдено, возвращает {@code -1}.
     *
     * @param x значение {@code x} для поиска
     * @return индекс значения {@code x} или {@code -1}, если не найдено
     */
    int indexOfX(double x);

    /**
     * Возвращает индекс указанного значения {@code y}.
     * <p>
     * Если значение {@code y} не найдено, возвращает {@code -1}.
     *
     * @param y значение {@code y} для поиска
     * @return индекс значения {@code y} или {@code -1}, если не найдено
     */
    int indexOfY(double y);

    /**
     * Возвращает самое левое значение {@code x}.
     *
     * @return самое левое значение {@code x}
     */
    double leftBound();

    /**
     * Возвращает самое правое значение {@code x}.
     *
     * @return самое правое значение {@code x}
     */
    double rightBound();
}
