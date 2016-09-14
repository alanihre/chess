package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class King extends Piece {

    public King(Point position, PieceColor color) {
        super(position, color);
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♚';
        } else {
            return '♔';
        }
    }

    public PieceType getType() {
        return PieceType.KING;
    }

    public boolean canMoveTo(Point newPosition) {
        //The king can move one step in all directions.

        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getX();

        return positionXDelta <= 1 && positionYDelta <= 1;
    }
}
