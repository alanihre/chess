package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.game_io_interface.GameIOInterface;
import com.alanihre.chess.piece.Piece;

public abstract class Game {

    private final GameIOInterface ioInterface;
    private Board board;
    private Piece.PieceColor currentMovingPieceColor;

    public Game(GameIOInterface ioInterface) {
        this.ioInterface = ioInterface;

        this.board = initializeBoard();

        currentMovingPieceColor = startingPieceColor();

        this.getIoInterface().gameReady(this);

        getIoInterface().requestMove(currentMovingPieceColor);
    }

    protected abstract Board initializeBoard();

    public Board getBoard() {
        return board;
    }

    protected GameIOInterface getIoInterface() {
        return ioInterface;
    }

    protected Piece.PieceColor getCurrentMovingPieceColor() {
        return currentMovingPieceColor;
    }

    protected void setCurrentMovingPieceColor(Piece.PieceColor color) {
        currentMovingPieceColor = color;
    }

    protected Piece.PieceColor startingPieceColor() {
        return Piece.PieceColor.WHITE;
    }

    public void movePiece(String oldPosition, String newPosition) {
        Point oldPositionPoint = getBoard().readableCoordinateToPoint(oldPosition);
        Point newPositionPoint = getBoard().readableCoordinateToPoint(newPosition);

        //Check if any movement was made at all
        if (Point.equalPoints(oldPositionPoint, newPositionPoint)) {
            //TODO: Throw error
            System.out.println("Old and new position are equal");
            return;
        }

        Piece piece = getBoard().getPieceAtPosition(oldPositionPoint);
        if (piece == null) {
            //TODO: Throw error
            System.out.println("There is no piece at the entered position");
            return;
        }

        if (!canMovePiece(piece, newPositionPoint)) {
            //TODO: Throw/bubble error
            return;
        }

        //Check if piece at target position can be captured
        Piece targetPositionPiece = getBoard().getPieceAtPosition(newPositionPoint);
        if (targetPositionPiece != null && pieceCanCapturePiece(piece, targetPositionPiece)) {
            capturePiece(targetPositionPiece);
        }

        getBoard().removePiece(piece);
        piece.moveTo(newPositionPoint);
        getBoard().addPiece(piece);

        String readableCoordinate = getBoard().boardPointToReadableCoordinate(piece.getPosition());
        getIoInterface().pieceMoved(piece, readableCoordinate);

        prepareForNextMove();
        getIoInterface().requestMove(getCurrentMovingPieceColor());

    }

    abstract void prepareForNextMove();

    protected boolean canMovePiece(Piece piece, Point newPosition) {
        if (getBoard().positionWithinBoardBounds(newPosition)) {
            //TODO: Throw error
            System.out.println("New position is outside board bounds");
            return false;
        }

        if (!piece.canMoveTo(newPosition)) {
            //TODO: Throw error
            System.out.println("This piece is not allowed to move to the entered position");
            return false;
        }

        return true;
    }

    abstract boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece);

    protected void capturePiece(Piece piece) {
        getBoard().removePiece(piece);
        String readableCoordinate = getBoard().boardPointToReadableCoordinate(piece.getPosition());
        getIoInterface().pieceCaptured(piece, readableCoordinate);
    }
}
