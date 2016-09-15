package com.alanihre.chess.piece;

public class PieceMovementNotSupportedException extends RuntimeException {
    public PieceMovementNotSupportedException() {
        super();
    }

    public PieceMovementNotSupportedException(String message) {
        super(message);
    }
}