package com.alanihre.chess.board;

import com.alanihre.chess.piece.Piece;

public class Square {

    private final Board board;
    private Piece piece;
    private Position position;

    public Square(Board board, Position position) {
        this.board = board;
        this.position = position;
    }

    public Board getBoard() {
        return board;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        piece.setSquare(this);
    }

    public void removePiece() {
        piece.setSquare(null);
        piece = null;
    }

    public boolean hasPiece() {
        return piece != null;
    }

}
