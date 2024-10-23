package ru.ssau.tk.nikitals.oop.exceptions;

/**
 * {@code InconsistentFunctionsException} является исключением времени выполнения,
 * которое выбрасывается при обнаружении несовместимых функций.
 *
 * @see RuntimeException
 */
public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
        super();
    }
    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
