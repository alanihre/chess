package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Queen extends Piece {

    public Queen(Point position, PieceColor color) {
        super(position, color);
    }

    public String getPieceName() {
        return "queen";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♛';
        } else {
            return '♕';
        }
    }

    public boolean canMoveTo(Point newPosition) {
        //The queen can move like both the rook and the bishop

        Point piecePosition = getPosition();
        int positionXDelta = Math.abs(newPosition.getX() - piecePosition.getX());
        int positionYDelta = Math.abs(newPosition.getY() - piecePosition.getY());

        return newPosition.getX() == piecePosition.getX()
                || newPosition.getY() == piecePosition.getY()
                || positionXDelta == positionYDelta;
    }
}
