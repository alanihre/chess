package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class King extends Piece {

    public King(Point position, PieceColor color) {
        super(position, color);
    }

    public String getPieceName() {
        return "king";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♚';
        } else {
            return '♔';
        }
    }

    public boolean canMoveTo(Point newPosition) {
        //The king can move one step in all directions.

        Point piecePosition = getPosition();
        int positionXDelta = Math.abs(newPosition.getX() - piecePosition.getX());
        int positionYDelta = Math.abs(newPosition.getY() - piecePosition.getY());

        return positionXDelta <= 1 && positionYDelta <= 1;
    }
}
