package com.alanihre.chess;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean equalPoints(Point point1, Point point2) {
        return point1.getX() == point2.getX() && point1.getY() == point2.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ("(" + x + "," + y + ")");
    }
}
