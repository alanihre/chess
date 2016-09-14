package com.alanihre.chess;

public class Pawn extends Piece {

    public Pawn (Point position, PieceColor color) {
        super(position, color);
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♟';
        } else {
            return '♙';
        }
    }

    public PieceType getType() {
        return PieceType.PAWN;
    }

    public boolean canMoveTo(Point newPosition) {
        //TODO: Implement respawn of pawn as other piece on position 8
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
                && (pieceColor == Piece.PieceColor.WHITE && positionYDelta < 0 && Math.abs(positionYDelta) <= movableSteps)
                || (pieceColor == Piece.PieceColor.BLACK && positionYDelta > 0 && positionYDelta <= movableSteps);
    }
}
