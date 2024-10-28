package ru.ssau.tk.nikitals.oop.exceptions;

import java.io.Serial;

/**
 * {@code ArrayHasDuplicateElementsException} является исключением времени выполнения,
 * которое выбрасывается при обнаружении дублирующихся элементов в массиве.
 *
 * <p>Это исключение может использоваться для указания на проблемы, связанные с
 * наличием повторяющихся элементов в массиве, что может привести к ошибкам в вычислениях
 * или обработке данных.
 *
 * <p>{@code ArrayHasDuplicateElementsException} и его подклассы являются <em>непроверяемыми
 * исключениями</em>. Непроверяемые исключения <em>не</em> нужно объявлять в
 * блоке {@code throws} метода или конструктора, если они могут быть выброшены
 * во время выполнения метода или конструктора и распространяться за их пределы.
 */
public class ArrayHasDuplicateElementsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -331072537981546969L;

    /**
     * Создает новое исключение дублирующихся элементов в массиве с {@code null} в качестве
     * сообщения. Причина не инициализируется и может быть впоследствии
     * инициализирована вызовом {@link #initCause}.
     */
    public ArrayHasDuplicateElementsException() {
        super();
    }

    /**
     * Создает новое исключение дублирующихся элементов в массиве с указанным сообщением.
     * Причина не инициализируется и может быть впоследствии инициализирована
     * вызовом {@link #initCause}.
     *
     * @param message сообщение. Сообщение сохраняется для последующего
     *                получения методом {@link #getMessage()}.
     */
    public ArrayHasDuplicateElementsException(String message) {
        super(message);
    }
}