package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.ClassicBoard;
import com.alanihre.chess.piece.Piece;

import java.util.List;

public class ClassicChessGame extends Game {

    public ClassicChessGame(GameDelegate ioInterface) {
        super(ioInterface);
    }

    protected Board initializeBoard() {
        Board board = new ClassicBoard();
        board.setupBoard();
        return board;
    }

    @Override
    protected boolean canMovePiece(Piece piece, Point newPosition) {
        if (!super.canMovePiece(piece, newPosition)) {
            return false;
        }

        if (!piece.canLeap()) {
            //Check if movement path is blocked by other pieces
            List<Point> movementPath = piece.getMovementPathToPosition(newPosition);
            int numberOfMoves = movementPath.size();
            for (int i = 0; i < numberOfMoves; i++) {
                Point point = movementPath.get(i);
                Piece pieceAtPosition = getBoard().getPieceAtPosition(point);
                if (pieceAtPosition != null) {
                    //If this is the last move in the path; check if the piece there can be captured
                    if (i != numberOfMoves - 1 || pieceAtPosition.getColor() == piece.getColor()) {
                        //TODO: Throw error
                        System.out.println("There are other pieces blocking the movement");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void prepareForNextMove() {
        if (getCurrentMovingPieceColor() == Piece.PieceColor.BLACK) {
            setCurrentMovingPieceColor(Piece.PieceColor.WHITE);
        } else {
            setCurrentMovingPieceColor(Piece.PieceColor.BLACK);
        }
    }

    protected boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece) {
        return targetPiece.getColor() != capturingPiece.getColor();
    }
}
