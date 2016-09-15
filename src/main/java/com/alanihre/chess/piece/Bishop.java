package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Bishop extends Piece {

    public Bishop(Point position, PieceColor color) {
        super(position, color);
    }

    public String getPieceName() {
        return "bishop";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♝';
        } else {
            return '♗';
        }
    }

    public boolean canMoveTo(Point newPosition) {
        //Bishops can only move diagonally which means that the delta of movement
        //in X and Y should be the same.

        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        return positionXDelta == positionYDelta;
    }
}
