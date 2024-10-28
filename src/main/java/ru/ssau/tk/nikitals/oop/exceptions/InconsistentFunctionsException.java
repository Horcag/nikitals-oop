package ru.ssau.tk.nikitals.oop.exceptions;

import java.io.Serial;

/**
 * {@code InconsistentFunctionsException} является исключением времени выполнения,
 * которое выбрасывается при обнаружении несоответствия между табулированными функциями.
 *
 * <p>Это исключение может использоваться для указания на проблемы, связанные с
 * несовпадением количества точек или значений x в табулированных функциях.
 *
 * <p>{@code InconsistentFunctionsException} и его подклассы являются <em>непроверяемыми
 * исключениями</em>. Непроверяемые исключения <em>не</em> нужно объявлять в
 * блоке {@code throws} метода или конструктора, если они могут быть выброшены
 * во время выполнения метода или конструктора и распространяться за их пределы.
 */
public class InconsistentFunctionsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7987652286752137765L;

    public InconsistentFunctionsException() {
        super();
    }
    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
