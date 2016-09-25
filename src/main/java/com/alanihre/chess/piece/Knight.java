package com.alanihre.chess.piece;

import com.alanihre.chess.piece.movement.KnightMovement;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);

        KnightMovement movement = new KnightMovement();
        setMovement(movement);
    }

    public String getPieceName() {
        return "knight";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♘';
        } else {
            return '♞';
        }
    }

    @Override
    public boolean canLeap() {
        return true;
    }
}
