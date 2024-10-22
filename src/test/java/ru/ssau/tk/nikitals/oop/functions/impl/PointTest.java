package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void x() {
        Point point = new Point(1, 2);
        assertEquals(point.x, 1);
    }

    @Test
    void y() {
        Point point = new Point(1, 2);
        assertEquals(point.y, 2);
    }

}