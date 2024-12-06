package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void x() {
        Point point = new Point(1, 2);
        assertEquals(1, point.x);
    }

    @Test
    void y() {
        Point point = new Point(1, 2);
        assertEquals(2, point.y);
    }

}