package com.alanihre.chess.board;

public class Offset {
    private int x;
    private int y;

    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHorizontal() {
        return x != 0;
    }

    public boolean isVertical() {
        return y != 0;
    }

    public boolean isOnlyHorizontal() {
        return isHorizontal() && !isVertical();
    }

    public boolean isOnlyVertical() {
        return isVertical() && !isHorizontal();
    }

    public boolean isDiagonal() {
        return isHorizontal() && isVertical() && Math.abs(x) == Math.abs(y);
    }

    public boolean isPositiveHorizontal() {
        return isHorizontal() && this.x > 0;
    }

    public boolean isPositiveVertical() {
        return isVertical() && this.y > 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}