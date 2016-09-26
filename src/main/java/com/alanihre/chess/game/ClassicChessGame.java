package com.alanihre.chess.game;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.ClassicBoard;
import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.King;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

public class ClassicChessGame extends Game {

    private boolean isCheckingForCheck = false;

    public ClassicChessGame(GameDelegate delegate) {
        super(delegate);
    }

    protected Board initializeBoard() {
        Board board = new ClassicBoard();
        board.setupBoard();
        return board;
    }

    @Override
    protected boolean canMakeMove(Square oldSquare, Square newSquare) {
        if (!super.canMakeMove(oldSquare, newSquare)) {
            return false;
        }

        Piece piece = oldSquare.getPiece();
        if (piece instanceof Pawn
                && ((Pawn) piece).canMakePassantCapture(newSquare)
                && !passantCapturePossible(piece, newSquare)) {
            return false;
        }

        if (!isCheckingForCheck || piece.getColor() != getCurrentMovingPieceColor()) {
            Square kingSquare;
            if (piece instanceof King) {
                kingSquare = newSquare;
            } else {
                Piece king = findKingOnBoard(piece.getColor());
                kingSquare = king.getSquare();
            }
            if (isCheck(piece.getColor(), kingSquare)) {
                //Move would put king in check
                return false;
            }
        }

        return true;
    }

    @Override
    protected void willMakeMove(Square oldSquare, Square newSquare) {
        super.willMakeMove(oldSquare, newSquare);

        Piece piece = oldSquare.getPiece();
        if (piece instanceof Pawn
                && ((Pawn) piece).canMakePassantCapture(newSquare)
                && passantCapturePossible(piece, newSquare)) {
            Piece lastMovedPiece = getLastMove().getPiece();
            capturePiece(lastMovedPiece);
        }
    }

    protected void pieceMoved(Piece piece, Position oldPosition) {
        super.pieceMoved(piece, oldPosition);

        checkPromotion(piece);

        if (isCheckMate(getCurrentMovingPieceColor()) || isCheckMate(Piece.PieceColor.invert(getCurrentMovingPieceColor()))) {
            getDelegate().gameEnded("Game ended because king is in check mate");
        }
    }

    private boolean passantCapturePossible(Piece capturingPiece, Square target) {
        Piece lastMovedPiece = getLastMove().getPiece();
        return lastMovedPiece != null && ((Pawn) capturingPiece).getPassantPiece(target) == lastMovedPiece;
    }


    private void checkPromotion(Piece piece) {
        if (piece.getClass() == Pawn.class) {
            Square square = piece.getSquare();
            Position piecePosition = square.getPosition();
            int piecePositionY = piecePosition.getRank();
            if (piecePositionY == 0 || piecePositionY == getBoard().getWidth() - 1) {
                PieceType[] pieceTypes = new PieceType[]{PieceType.BISHOP, PieceType.KNIGHT, PieceType.QUEEN, PieceType.ROOK};
                PieceType pieceType = getDelegate().requestNewPieceOfType(pieceTypes, "Promotion. Please pick another piece to replace the pawn.");
                Piece.PieceColor pieceColor = getCurrentMovingPieceColor();
                Piece newPiece = PieceType.pieceFromType(pieceType, pieceColor);
                if (newPiece instanceof Pawn || newPiece instanceof King) {
                    throw new GamePlayException("Invalid piece type");
                } else {
                    square.removePiece();
                    square.setPiece(newPiece);
                }
            }
        }
    }

    //TODO: Move into king piece
    private boolean isCheck(Piece.PieceColor pieceColor, Square kingSquare) {
        for (int file = 0; file < getBoard().getWidth(); file++) {
            for (int rank = 0; rank < getBoard().getHeight(); rank++) {
                Square square = getBoard().getSquareAtPosition(new Position(file, rank));
                Piece piece = square.getPiece();
                if (piece != null && piece.getColor() != pieceColor) {
                    isCheckingForCheck = true;
                    if (canMakeMove(square, kingSquare)) {
                        isCheckingForCheck = false;
                        return true;
                    }
                }
            }
        }
        isCheckingForCheck = false;
        return false;
    }

    private Piece findKingOnBoard(Piece.PieceColor pieceColor) {
        for (int file = 0; file < getBoard().getWidth(); file++) {
            for (int rank = 0; rank < getBoard().getHeight(); rank++) {
                Piece piece = getBoard().getSquareAtPosition(new Position(file, rank)).getPiece();
                if (piece != null & piece instanceof King && piece.getColor() == pieceColor) {
                    return piece;
                }
            }
        }
        throw new RuntimeException("No king on board with color " + pieceColor.toString());
    }

    private boolean isCheckMate(Piece.PieceColor pieceColor) {
        Piece king = findKingOnBoard(pieceColor);
        Square kingSquare = king.getSquare();
        Position kingPosition = kingSquare.getPosition();

        if (!isCheck(pieceColor, kingSquare)) {
            return false;
        }

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Position position = new Position(kingPosition.getFile() + i, kingPosition.getRank() + j);
                if (!getBoard().positionWithinBoardBounds(position)) {
                    continue;
                }
                Square square = getBoard().getSquareAtPosition(position);
                if (canMakeMove(kingSquare, square)) {
                    //TODO: Check isCheck here as well since it maybe can move but still does not get it out of check
                    isCheckingForCheck = false;
                    return true;
                }
            }
        }

        return false;
    }
}
