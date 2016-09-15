package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Rook extends Piece {

    public Rook(Point position, PieceColor color) {
        super(PieceType.ROOK, position, color);
    }

    public String getPieceName() {
        return "rook";
    }

    public boolean canMoveTo(Point newPosition) {
        //Rooks can be moved any number of squares up, down, left or right.

        Point piecePosition = getPosition();
        return newPosition.getX() == piecePosition.getX() || newPosition.getY() == piecePosition.getY();
    }
}
