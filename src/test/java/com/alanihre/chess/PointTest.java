package com.alanihre.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointTest {

    private Point point;

    @Before
    public void setUp() {
        point = new Point(1, 2);
    }

    @Test
    public void testConstructor() {
        assertEquals(point.getX(), 1);
        assertEquals(point.getY(), 2);
    }

    @Test
    public void testEqualPoints() {
        Point point2 = new Point(1, 2);
        Point point3 = new Point(1, 3);
        assertTrue(point.equals(point2));
        assertFalse(point.equals(point3));
    }

    @Test
    public void testToString() {
        String pointAsString = point.toString();
        assertEquals("(1,2)", pointAsString);
    }
}
