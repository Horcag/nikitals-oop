package ru.ssau.tk.nikitals.oop.core.domain.exceptions;

import java.io.Serial;

/**
 * {@code InterpolationException} является исключением времени выполнения,
 * которое выбрасывается при возникновении ошибки в процессе интерполяции.
 *
 * <p>Это исключение может использоваться для указания на различные проблемы,
 * такие как недопустимые входные данные или ошибки вычислений во время интерполяции.
 *
 * <p>{@code InterpolationException} и его подклассы являются <em>непроверяемыми
 * исключениями</em>. Непроверяемые исключения <em>не</em> нужно объявлять в
 * блоке {@code throws} метода или конструктора, если они могут быть выброшены
 * во время выполнения метода или конструктора и распространяться за их пределы.
 */
public class InterpolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8209937333453924149L;

    /**
     * Создает новое исключение интерполяции с {@code null} в качестве
     * сообщения. Причина не инициализируется и может быть впоследствии
     * инициализирована вызовом {@link #initCause}.
     */
    public InterpolationException() {
        super();
    }

    /**
     * Создает новое исключение интерполяции с указанным сообщением.
     * Причина не инициализируется и может быть впоследствии инициализирована
     * вызовом {@link #initCause}.
     *
     * @param message сообщение. Сообщение сохраняется для последующего
     *                получения методом {@link #getMessage()}.
     */
    public InterpolationException(String message) {
        super(message);
    }
}
