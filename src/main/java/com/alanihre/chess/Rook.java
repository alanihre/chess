package com.alanihre.chess;

public class Rook extends Piece {

    public Rook (Point position, PieceColor color) {
        super(position, color);
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♜';
        } else {
            return '♖';
        }
    }

    public PieceType getType() {
        return PieceType.ROOK;
    }

    public boolean canMoveTo(Point newPosition) {
        //Rooks can be moved any number of squares up, down, left or right.

        Point piecePosition = getPosition();
        return newPosition.getX() == piecePosition.getX() || newPosition.getY() == piecePosition.getY();
    }
}
