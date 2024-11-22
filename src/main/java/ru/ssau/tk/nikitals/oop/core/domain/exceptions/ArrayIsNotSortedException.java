package ru.ssau.tk.nikitals.oop.core.domain.exceptions;

import java.io.Serial;

/**
 * {@code ArrayIsNotSortedException} является исключением времени выполнения,
 * которое выбрасывается при обнаружении неотсортированного массива.
 *
 * <p>Это исключение может использоваться для указания на проблемы, связанные с
 * неупорядоченностью элементов массива, что может привести к ошибкам в вычислениях
 * или обработке данных.
 *
 * <p>{@code ArrayIsNotSortedException} и его подклассы являются <em>непроверяемыми
 * исключениями</em>. Непроверяемые исключения <em>не</em> нужно объявлять в
 * блоке {@code throws} метода или конструктора, если они могут быть выброшены
 * во время выполнения метода или конструктора и распространяться за их пределы.
 */
public class ArrayIsNotSortedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -6295877370622648523L;

    /**
     * Создает новое исключение неотсортированного массива с {@code null} в качестве
     * сообщения. Причина не инициализируется и может быть впоследствии
     * инициализирована вызовом {@link #initCause}.
     */
    public ArrayIsNotSortedException() {
        super();
    }

    /**
     * Создает новое исключение неотсортированного массива с указанным сообщением.
     * Причина не инициализируется и может быть впоследствии инициализирована
     * вызовом {@link #initCause}.
     *
     * @param message сообщение. Сообщение сохраняется для последующего
     *                получения методом {@link #getMessage()}.
     */
    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
