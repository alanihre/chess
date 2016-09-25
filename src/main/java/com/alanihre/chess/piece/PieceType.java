package com.alanihre.chess.piece;

public enum PieceType {
    BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;

    public static Piece pieceFromType(PieceType type, Piece.PieceColor color) {
        Piece newPiece;
        switch (type) {
            case BISHOP:
                newPiece = new Bishop(color);
                break;
            case KING:
                newPiece = new King(color);
                break;
            case KNIGHT:
                newPiece = new Knight(color);
                break;
            case PAWN:
                newPiece = new Pawn(color);
                break;
            case QUEEN:
                newPiece = new Queen(color);
                break;
            case ROOK:
                newPiece = new Rook(color);
                break;
            default:
                throw new RuntimeException("Unsupported piece type");
        }
        return newPiece;
    }
}
