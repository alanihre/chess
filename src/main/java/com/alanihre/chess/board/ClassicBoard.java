package com.alanihre.chess.board;

import com.alanihre.chess.piece.Bishop;
import com.alanihre.chess.piece.King;
import com.alanihre.chess.piece.Knight;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.Queen;
import com.alanihre.chess.piece.Rook;

public class ClassicBoard extends Board {

    private static final int CLASSIC_BOARD_SIZE_WIDTH = 8;
    private static final int CLASSIC_BOARD_SIZE_HEIGHT = 8;

    public ClassicBoard() {
        super(CLASSIC_BOARD_SIZE_WIDTH, CLASSIC_BOARD_SIZE_HEIGHT);
    }

    public void setupBoard() {
        //Create 16 pawns
        for (int i = 0; i < 16; i++) {
            Position pawnPosition;
            Piece.PieceColor pawnColor;
            if (i < 8) {
                pawnPosition = new Position(i, 1);
                pawnColor = Piece.PieceColor.BLACK;
            } else {
                pawnPosition = new Position(i - 8, 6);
                pawnColor = Piece.PieceColor.WHITE;
            }
            Pawn pawn = new Pawn(pawnColor);
            putPiece(pawn, pawnPosition);
        }

        //Create 4 knights
        Knight knight1 = new Knight(Piece.PieceColor.BLACK);
        putPiece(knight1, new Position(1, 0));
        Knight knight2 = new Knight(Piece.PieceColor.BLACK);
        putPiece(knight2, new Position(6, 0));
        Knight knight3 = new Knight(Piece.PieceColor.WHITE);
        putPiece(knight3, new Position(1, 7));
        Knight knight4 = new Knight(Piece.PieceColor.WHITE);
        putPiece(knight4, new Position(6, 7));

        //Create 4 rooks
        Rook rook1 = new Rook(Piece.PieceColor.BLACK);
        putPiece(rook1, new Position(0, 0));
        Rook rook2 = new Rook(Piece.PieceColor.BLACK);
        putPiece(rook2, new Position(7, 0));
        Rook rook3 = new Rook(Piece.PieceColor.WHITE);
        putPiece(rook3, new Position(0, 7));
        Rook rook4 = new Rook(Piece.PieceColor.WHITE);
        putPiece(rook4, new Position(7, 7));

        //Create 4 bishops
        Bishop bishop1 = new Bishop(Piece.PieceColor.BLACK);
        putPiece(bishop1, new Position(2, 0));
        Bishop bishop2 = new Bishop(Piece.PieceColor.BLACK);
        putPiece(bishop2, new Position(5, 0));
        Bishop bishop3 = new Bishop(Piece.PieceColor.WHITE);
        putPiece(bishop3, new Position(2, 7));
        Bishop bishop4 = new Bishop(Piece.PieceColor.WHITE);
        putPiece(bishop4, new Position(5, 7));

        //Create 2 queens
        Queen queen1 = new Queen(Piece.PieceColor.BLACK);
        putPiece(queen1, new Position(3, 0));
        Queen queen2 = new Queen(Piece.PieceColor.WHITE);
        putPiece(queen2, new Position(3, 7));

        //Create 2 kings
        King king1 = new King(Piece.PieceColor.BLACK);
        putPiece(king1, new Position(4, 0));
        King king2 = new King(Piece.PieceColor.WHITE);
        putPiece(king2, new Position(4, 7));
    }

}
