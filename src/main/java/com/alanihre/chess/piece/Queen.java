package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Queen extends Piece {

    public Queen(Point position, PieceColor color) {
        super(position, color);
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♛';
        } else {
            return '♕';
        }
    }

    public PieceType getType() {
        return PieceType.QUEEN;
    }

    public boolean canMoveTo(Point newPosition) {
        //The queen can move like both the rook and the bishop

        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        return newPosition.getX() == piecePosition.getX()
                || newPosition.getY() == piecePosition.getY()
                || positionXDelta == positionYDelta;
    }
}
