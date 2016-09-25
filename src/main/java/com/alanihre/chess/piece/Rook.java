package com.alanihre.chess.piece;

import com.alanihre.chess.piece.movement.FourDirectionMovement;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);

        FourDirectionMovement movement = new FourDirectionMovement();
        setMovement(movement);
    }

    public String getPieceName() {
        return "rook";
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♖';
        } else {
            return '♜';
        }
    }
}
