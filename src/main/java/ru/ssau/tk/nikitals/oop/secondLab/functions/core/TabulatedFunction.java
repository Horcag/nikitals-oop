package ru.ssau.tk.nikitals.oop.secondLab.functions.core;

/**
 * Интерфейс, представляющий табулированную функцию.
 * Расширяет интерфейс MathFunction и добавляет методы, специфичные для табулированных функций.
 */
public interface TabulatedFunction extends MathFunction {

    /**
     * Возвращает количество табулированных значений.
     *
     * @return количество табулированных значений
     */
    int getCount();

    /**
     * Возвращает значение <code>x</code> по указанному индексу.
     *
     * @param index индекс значения <code>x</code>
     * @return значение <code>x</code> по указанному индексу
     */
    double getX(int index);

    /**
     * Возвращает значение <code>y</code> по указанному индексу.
     *
     * @param index индекс значения <code>y</code>
     * @return значение <code>y</code> по указанному индексу
     */
    double getY(int index);

    /**
     * Устанавливает значение <code>y</code> по указанному индексу.
     *
     * @param index индекс значения <code>y</code>
     * @param value значение <code>y</code>
     */
    void setY(int index, double value);

    /**
     * Возвращает индекс указанного значения <code>x</code>.
     * Если значение <code>x</code> не найдено, возвращает <code>-1</code>.
     *
     * @param x значение <code>x</code> для поиска
     * @return индекс значения <code>x</code> или <code>-1</code>, если не найдено
     */
    int indexOfX(double x);

    /**
     * Возвращает индекс указанного значения <code>y</code>.
     * Если значение <code>y</code> не найдено, возвращает <code>-1</code>.
     *
     * @param y значение <code>y</code> для поиска
     * @return индекс значения <code>y</code> или <code>-1</code>, если не найдено
     */
    int indexOfY(double y);

    /**
     * Возвращает самое левое значение <code>x</code>.
     *
     * @return самое левое значение <code>x</code>
     */
    double leftBound();

    /**
     * Возвращает самое правое значение <code>x</code>.
     *
     * @return самое правое значение <code>x</code
     */
    double rightBound();
}
