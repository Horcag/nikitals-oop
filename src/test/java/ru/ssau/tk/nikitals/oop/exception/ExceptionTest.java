package ru.ssau.tk.nikitals.oop.exception;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    @Test
    public void testArrayHasDuplicateElementsException() {
        assertThrows(ArrayHasDuplicateElementsException.class, () -> {
            throw new ArrayHasDuplicateElementsException();
        });
    }

    @Test
    public void testArrayIsNotSortedElementsException() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            throw new ArrayIsNotSortedException();
        });
    }

    @Test
    public void testDifferentLengthOfArraysException() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            throw new DifferentLengthOfArraysException();
        });
    }

    @Test
    public void testInconsistentFunctionsException() {
        assertThrows(InconsistentFunctionsException.class, () -> {
            throw new InconsistentFunctionsException();
        });
    }

    @Test
    public void testInterpolationException() {
        assertThrows(InterpolationException.class, () -> {
            throw new InterpolationException();
        });
    }

}
