package com.alanihre.chess.game;

import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.Piece;

class Move {
    private Piece piece;
    private Square origin;
    private Square target;

    public Move(Square origin, Square target) {
        this.origin = origin;
        this.target = target;
        piece = this.origin.getPiece();
    }

    public Square getOrigin() {
        return origin;
    }

    public Square getTarget() {
        return target;
    }

    public Piece getPiece() {
        return piece;
    }
}