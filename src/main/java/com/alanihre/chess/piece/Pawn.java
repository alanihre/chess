package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public class Pawn extends Piece {

    public Pawn(Point position, PieceColor color) {
        super(PieceType.PAWN, position, color);
    }

    public String getPieceName() {
        return "pawn";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♟';
        } else {
            return '♙';
        }
    }

    public boolean canMoveTo(Point newPosition) {
        //Pawns can move one step forward. On it's first move it can move 2 steps.

        int movableSteps = 1;

        if (getNumberOfMoves() == 0) {
            movableSteps = 2;
        }

        Piece.PieceColor pieceColor = getColor();
        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        return positionXDelta == 0
                && ((pieceColor == Piece.PieceColor.WHITE && positionYDelta < 0 && Math.abs(positionYDelta) <= movableSteps)
                || (pieceColor == Piece.PieceColor.BLACK && positionYDelta > 0 && positionYDelta <= movableSteps));
    }

    public boolean canMakeCapturingMove(Point newPosition) {
        Piece.PieceColor pieceColor = getColor();
        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        return Math.abs(positionXDelta) == Math.abs(positionYDelta)
                && ((pieceColor == Piece.PieceColor.WHITE && positionYDelta == -1)
                || (pieceColor == Piece.PieceColor.BLACK && positionYDelta == 1));
    }
}
