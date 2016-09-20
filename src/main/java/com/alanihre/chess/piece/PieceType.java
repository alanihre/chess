package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

public enum PieceType {
    BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;

    public static Piece pieceFromType(PieceType type, Point position, Piece.PieceColor color) {
        Piece newPiece;
        switch (type) {
            case BISHOP:
                newPiece = new Bishop(position, color);
                break;
            case KING:
                newPiece = new King(position, color);
                break;
            case KNIGHT:
                newPiece = new Knight(position, color);
                break;
            case PAWN:
                newPiece = new Pawn(position, color);
                break;
            case QUEEN:
                newPiece = new Queen(position, color);
                break;
            case ROOK:
                newPiece = new Rook(position, color);
                break;
            default:
                throw new RuntimeException("Unsupported piece type");
        }
        return newPiece;
    }
}
