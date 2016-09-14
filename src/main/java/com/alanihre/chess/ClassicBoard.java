package com.alanihre.chess;

import java.util.List;

public class ClassicBoard extends Board {

    private static final int CLASSIC_BOARD_SIZE_WIDTH = 8;
    private static final int CLASSIC_BOARD_SIZE_HEIGHT = 8;

    public ClassicBoard() {
        super(CLASSIC_BOARD_SIZE_WIDTH, CLASSIC_BOARD_SIZE_HEIGHT);
    }

    public void setupBoard() {
        //Create 16 pawns
        for (int i = 0; i < 16; i++) {
            Pawn pawn;
            if (i < 8) {
                pawn = new Pawn(new Point(i, 1), Piece.PieceColor.BLACK);
            } else {
                pawn = new Pawn(new Point(i - 8, 6), Piece.PieceColor.WHITE);
            }
            addPiece(pawn);
        }

        //Create 4 knights
        Knight knight1 = new Knight(new Point(1, 0), Piece.PieceColor.BLACK);
        addPiece(knight1);
        Knight knight2 = new Knight(new Point(6, 0), Piece.PieceColor.BLACK);
        addPiece(knight2);
        Knight knight3 = new Knight(new Point(1, 7), Piece.PieceColor.WHITE);
        addPiece(knight3);
        Knight knight4 = new Knight(new Point(6, 7), Piece.PieceColor.WHITE);
        addPiece(knight4);

        //Create 4 rooks
        Rook rook1 = new Rook(new Point(0, 0), Piece.PieceColor.BLACK);
        addPiece(rook1);
        Rook rook2 = new Rook(new Point(7, 0), Piece.PieceColor.BLACK);
        addPiece(rook2);
        Rook rook3 = new Rook(new Point(0, 7), Piece.PieceColor.WHITE);
        addPiece(rook3);
        Rook rook4 = new Rook(new Point(7, 7), Piece.PieceColor.WHITE);
        addPiece(rook4);

        //Create 4 bishops
        Bishop bishop1 = new Bishop(new Point(2, 0), Piece.PieceColor.BLACK);
        addPiece(bishop1);
        Bishop bishop2 = new Bishop(new Point(5, 0), Piece.PieceColor.BLACK);
        addPiece(bishop2);
        Bishop bishop3 = new Bishop(new Point(2, 7), Piece.PieceColor.WHITE);
        addPiece(bishop3);
        Bishop bishop4 = new Bishop(new Point(5, 7), Piece.PieceColor.WHITE);
        addPiece(bishop4);

        //Create 2 queens
        Queen queen1 = new Queen(new Point(3, 0), Piece.PieceColor.BLACK);
        addPiece(queen1);
        Queen queen2 = new Queen(new Point(3, 7), Piece.PieceColor.WHITE);
        addPiece(queen2);

        //Create 2 kings
        King king1 = new King(new Point(4, 0), Piece.PieceColor.BLACK);
        addPiece(king1);
        King king2 = new King(new Point(4, 7), Piece.PieceColor.WHITE);
        addPiece(king2);
    }

}
