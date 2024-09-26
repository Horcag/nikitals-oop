package ru.ssau.tk.nikitals.oop.secondLab.functions.core;

/**
 * Абстрактный класс, представляющий табулированную функцию.
 */
public abstract class AbstractTabulatedFunction implements TabulatedFunction, MathFunction {

    /**
     * Возвращает индекс ближайшего меньшего или равного значения <code>x</code>.
     * <pre>
     * Если все значения <code>x</code> больше заданного, возвращает <code>0</code>.
     * Если все значения <code>x</code> меньше заданного, возвращает длину массива значений <code>x</code> <code>(count)</code>.
     *</pre>
     * @param x значение <code>x</code> для поиска
     * @return индекс ближайшего меньшего или равного значения <code>x</code>
     */
    abstract protected int floorIndexOfX(double x);

    /**
     * Экстраполирует значение функции слева от табулированных значений.
     *
     * @param x значение <code>x</code> для экстраполяции
     * @return экстраполированное значение функции
     */
    abstract protected double extrapolateLeft(double x);

    /**
     * Экстраполирует значение функции справа от табулированных значений.
     *
     * @param x значение <code>x</code> для экстраполяции
     * @return экстраполированное значение функции
     */
    abstract protected double extrapolateRight(double x);

    /**
     * Интерполирует значение функции для заданного <code>x</code>.
     *
     * @param x          значение <code>x</code> для интерполяции
     * @param floorIndex индекс ближайшего меньшего или равного значения <code>x</code>
     * @return интерполированное значение функции
     */
    abstract protected double interpolate(double x, int floorIndex);

    /**
     * Интерполирует значение функции для заданного <code>x</code>.
     *
     * @param x       значение <code>x</code> для интерполяции
     * @param leftX   значение <code>x</code> слева от интерполируемого значения
     * @param leftY   значение функции слева от интерполируемого значения
     * @param rightX  значение <code>x</code> справа от интерполируемого значения
     * @param rightY  значение функции справа от интерполируемого значения
     * @return интерполированное значение функции
     */
    protected double interpolate(double x, double leftX, double leftY, double rightX, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    /**
     * Применяет табулированную функцию к заданному аргументу <code>x</code>.
     * <pre>
     * Если <code>x</code> меньше левой границы, выполняется экстраполяция слева.
     * Если <code>x</code> больше правой границы, выполняется экстраполяция справа.
     * Если <code>x</code> совпадает с одним из табулированных значений <code>x</code>, возвращается соответствующее значение <code>y</code>.
     * Если <code>x</code> находится между табулированными значениями, выполняется интерполяция.
     * </pre>
     *
     * @param x аргумент функции
     * @return значение функции в точке <code>x</code>
     */
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

}
