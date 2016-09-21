package com.alanihre.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointTest {

    private static int POINT_X = 1;
    private static int POINT_Y = 2;

    private Point point;

    @Before
    public void setUp() {
        point = new Point(POINT_X, POINT_Y);
    }

    @Test
    public void testConstructor() {
        assertEquals(POINT_X, point.getX());
        assertEquals(POINT_Y, point.getY());
    }

    @Test
    public void testEqualPoints() {
        Point point2 = new Point(POINT_X, POINT_Y);
        assertTrue(point.equals(point2));
    }

    @Test
    public void testNotEqualPoints() {
        Point point3 = new Point(1, 3);
        assertFalse(point.equals(point3));
    }

    @Test
    public void testToString() {
        String pointAsString = point.toString();
        assertEquals("(" + POINT_X + "," + POINT_Y + ")", pointAsString);
    }
}
