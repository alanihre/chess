package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.piece.Piece;

public abstract class Game {

    private final GameDelegate delegate;
    private Board board;
    private Piece.PieceColor currentMovingPieceColor;

    public Game(GameDelegate delegate) {
        this.delegate = delegate;

        this.board = initializeBoard();

        currentMovingPieceColor = startingPieceColor();

        if (getDelegate() != null) {
            this.getDelegate().gameReady(this);
            getDelegate().requestMove(currentMovingPieceColor);
        }
    }

    protected abstract Board initializeBoard();

    public Board getBoard() {
        return board;
    }

    protected GameDelegate getDelegate() {
        return delegate;
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
        Point oldPositionPoint = getBoard().labeledPointToBoardPoint(oldPosition);
        Point newPositionPoint = getBoard().labeledPointToBoardPoint(newPosition);

        //Check if any movement was made at all
        if (oldPositionPoint.equals(newPositionPoint)) {
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

        String readableCoordinate = getBoard().boardPointToLabeledPoint(piece.getPosition());
        if (getDelegate() != null) {
            getDelegate().pieceMoved(piece, readableCoordinate);
        }

        prepareForNextMove();
        if (getDelegate() != null) {
            getDelegate().requestMove(getCurrentMovingPieceColor());
        }
    }

    abstract void prepareForNextMove();

    protected boolean canMovePiece(Piece piece, Point newPosition) {
        if (getBoard().positionWithinBoardBounds(newPosition)) {
            //TODO: Throw error
            System.out.println("New position is outside board bounds");
            return false;
        }

        if (piece.getColor() != getCurrentMovingPieceColor()) {
            //TODO: Throw error
            System.out.println("The piece at the entered position can't be moved by you. It has wrong color.");
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
        String readableCoordinate = getBoard().boardPointToLabeledPoint(piece.getPosition());
        if (getDelegate() != null) {
            getDelegate().pieceCaptured(piece, readableCoordinate);
        }
    }
}
