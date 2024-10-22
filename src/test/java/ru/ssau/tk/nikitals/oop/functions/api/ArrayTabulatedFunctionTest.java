package ru.ssau.tk.nikitals.oop.functions.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArrayTabulatedFunctionTest {
    private AbstractTabulatedFunction mockFunction;

    @BeforeEach
    void setUp() {
        mockFunction = Mockito.mock(AbstractTabulatedFunction.class);
        when(mockFunction.leftBound()).thenReturn(0.0);
        when(mockFunction.rightBound()).thenReturn(1.0);
        when(mockFunction.indexOfX(0.5)).thenReturn(-1);
        when(mockFunction.floorIndexOfX(0.5)).thenReturn(0);
    }


    @Test
    void apply() {
        // Тестируем метод apply для значения внутри границ
        when(mockFunction.interpolate(0.5, 0)).thenReturn(0.5);
        when(mockFunction.apply(0.5)).thenCallRealMethod();
        assertEquals(0.5, mockFunction.apply(0.5), 1e-5);
    }

    @Test
    void interpolate() {
        // Тестируем метод interpolate для заданных значений
        when(mockFunction.interpolate(0.5, 0, 0, 1, 1)).thenCallRealMethod();
        assertEquals(0.5, mockFunction.interpolate(0.5, 0, 0, 1, 1), 1e-5);
    }

    @Test
    void applyLeftExtrapolation() {
        // Тестируем метод apply для значения меньше левой границы
        when(mockFunction.extrapolateLeft(-0.5)).thenReturn(-0.5);
        when(mockFunction.apply(-0.5)).thenCallRealMethod();
        assertEquals(-0.5, mockFunction.apply(-0.5), 1e-5);
    }

    @Test
    void applyRightExtrapolation() {
        // Тестируем метод apply для значения больше правой границы
        when(mockFunction.extrapolateRight(1.5)).thenReturn(1.5);
        when(mockFunction.apply(1.5)).thenCallRealMethod();
        assertEquals(1.5, mockFunction.apply(1.5), 1e-5);
    }

    @Test
    void applyExactMatch() {
        // Тестируем метод apply для значения, совпадающего с табулированным значением
        when(mockFunction.indexOfX(0.0)).thenReturn(0);
        when(mockFunction.getY(0)).thenReturn(0.0);
        when(mockFunction.apply(0.0)).thenCallRealMethod();
        assertEquals(0.0, mockFunction.apply(0.0), 1e-5);
    }

    @Test
    void interpolateDifferentValues() {
        // Тестируем метод interpolate для других значений
        when(mockFunction.interpolate(0.75, 0, 0, 1, 1)).thenCallRealMethod();
        when(mockFunction.interpolate(0.25, 0, 0, 1, 1)).thenCallRealMethod();
        assertEquals(0.75, mockFunction.interpolate(0.75, 0, 0, 1, 1), 1e-5);
        assertEquals(0.25, mockFunction.interpolate(0.25, 0, 0, 1, 1), 1e-5);
    }

}