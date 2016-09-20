package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.ClassicBoard;
import com.alanihre.chess.piece.King;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

import java.util.List;

public class ClassicChessGame extends Game {

    private Piece lastMovedPiece;
    private boolean isPassantMove;

    public ClassicChessGame(GameDelegate delegate) {
        super(delegate);
    }

    protected Board initializeBoard() {
        Board board = new ClassicBoard();
        board.setupBoard();
        return board;
    }

    @Override
    protected boolean canMovePiece(Piece piece, Point newPosition) {
        if (!piece.canMoveTo(newPosition)) {
            if (piece instanceof Pawn) {
                Piece pieceAtPosition = getBoard().getPieceAtPosition(newPosition);
                if (!checkPassant(piece, newPosition)
                        && (pieceAtPosition == null || !pieceCanCapturePiece(piece, pieceAtPosition))) {
                    throw new InvalidMoveException("This piece is not allowed to move to the entered position");
                }
            } else {
                throw new InvalidMoveException("This piece is not allowed to move to the entered position");
            }
        }

        if (!piece.canLeap()) {
            //Check if movement path is blocked by other pieces
            List<Point> movementPath = piece.getMovementPathToPosition(newPosition);
            int numberOfMoves = movementPath.size();
            for (int i = 0; i < numberOfMoves; i++) {
                Point point = movementPath.get(i);
                Piece pieceAtPosition = getBoard().getPieceAtPosition(point);
                //If this is the last move in the path; check if the piece there can be captured
                if (pieceAtPosition != null
                        && (i != numberOfMoves - 1 || !pieceCanCapturePiece(piece, pieceAtPosition))) {
                        throw new InvalidMoveException("There are other pieces blocking the movement");
                }
            }
        }

        return true;
    }

    protected void pieceMoved(Piece piece, Point oldPosition) {
        if (isPassantMove) {
            isPassantMove = false;
            capturePiece(lastMovedPiece);
        }
        lastMovedPiece = piece;
        checkPromotion(piece);
    }

    private void checkPromotion(Piece piece) {
        if (piece.getClass() == Pawn.class) {
            Point piecePosition = piece.getPosition();
            int piecePositionY = piecePosition.getY();
            if (piecePositionY == 0 || piecePositionY == getBoard().getWidth() - 1) {
                PieceType[] pieceTypes = new PieceType[]{PieceType.BISHOP, PieceType.KNIGHT, PieceType.QUEEN, PieceType.ROOK};
                PieceType pieceType = getDelegate().requestNewPieceOfType(pieceTypes, "Promotion. Please pick another piece to replace the pawn.");
                Piece.PieceColor pieceColor = getCurrentMovingPieceColor();
                Piece newPiece = PieceType.pieceFromType(pieceType, piecePosition, pieceColor);
                if (newPiece instanceof Pawn || newPiece instanceof King) {
                    throw new GamePlayException("Invalid piece type");
                } else {
                    getBoard().putPiece(newPiece);
                }
            }
        }
    }

    private boolean checkPassant(Piece piece, Point newPosition) {
        Pawn pawn;
        if (piece instanceof Pawn && lastMovedPiece != null && lastMovedPiece instanceof Pawn) {
            pawn = (Pawn)piece;
        } else {
            return false;
        }
        int piecePositionX = piece.getPosition().getX();
        int lastMovedPiecePositionX = lastMovedPiece.getPosition().getX();

        return lastMovedPiece.getNumberOfMoves() == 1
                && (lastMovedPiecePositionX == piecePositionX + 1 || lastMovedPiecePositionX == piecePositionX - 1)
                && pawn.canMakeCapturingMove(newPosition);
    }

    protected void willMovePiece(Piece piece, Point newPosition) {
        isPassantMove = checkPassant(piece, newPosition);
    }

    public void prepareForNextMove() {
        if (getCurrentMovingPieceColor() == Piece.PieceColor.BLACK) {
            setCurrentMovingPieceColor(Piece.PieceColor.WHITE);
        } else {
            setCurrentMovingPieceColor(Piece.PieceColor.BLACK);
        }
    }

    protected boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece) {
        if (capturingPiece instanceof Pawn) {
            Pawn pawn = (Pawn)capturingPiece;
            if (!pawn.canMakeCapturingMove(targetPiece.getPosition())) {
                return false;
            }
        }
        return targetPiece.getColor() != capturingPiece.getColor();
    }
}
