package ru.ssau.tk.nikitals.oop.core.domain.exceptions;

import java.io.Serial;

/**
 * {@code DifferentLengthOfArraysException} является исключением времени выполнения,
 * которое выбрасывается при обнаружении массивов разной длины.
 *
 * <p>Это исключение может использоваться для указания на проблемы, связанные с
 * несовпадением длины массивов, что может привести к ошибкам в вычислениях или
 * обработке данных.
 *
 * <p>{@code DifferentLengthOfArraysException} и его подклассы являются <em>непроверяемыми
 * исключениями</em>. Непроверяемые исключения <em>не</em> нужно объявлять в
 * блоке {@code throws} метода или конструктора, если они могут быть выброшены
 * во время выполнения метода или конструктора и распространяться за их пределы.
 */
public class DifferentLengthOfArraysException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4533886957215809684L;

    /**
     * Создает новое исключение разной длины массивов с {@code null} в качестве
     * сообщения. Причина не инициализируется и может быть впоследствии
     * инициализирована вызовом {@link #initCause}.
     */
    public DifferentLengthOfArraysException() {
        super();
    }

    /**
     * Создает новое исключение разной длины массивов с указанным сообщением.
     * Причина не инициализируется и может быть впоследствии инициализирована
     * вызовом {@link #initCause}.
     *
     * @param message сообщение. Сообщение сохраняется для последующего
     *                получения методом {@link #getMessage()}.
     */
    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
