package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Bishop extends Piece {

    public Bishop(Point position, PieceColor color) {
        super(PieceType.BISHOP, position, color);
    }

    public String getPieceName() {
        return "bishop";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♗';
        } else {
            return '♝';

        }
    }

    public boolean canMoveTo(Point newPosition) {
        //Bishops can only move diagonally which means that the absolute value of the delta
        //of movement in X and Y should be the same.

        Point piecePosition = getPosition();
        int positionXDelta = Math.abs(newPosition.getX() - piecePosition.getX());
        int positionYDelta = Math.abs(newPosition.getY() - piecePosition.getY());

        return positionXDelta == positionYDelta;
    }
}
