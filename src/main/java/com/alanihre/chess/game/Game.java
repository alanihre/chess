package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.piece.Piece;

public abstract class Game {

    private final GameDelegate delegate;
    private Board board;
    private Piece.PieceColor currentMovingPieceColor;

    Game(GameDelegate delegate) {
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
            throw new InvalidMoveException("Old and new position are equal");
        }

        Piece piece = getBoard().getPieceAtPosition(oldPositionPoint);
        if (piece == null) {
            throw new InvalidMoveException("There is no piece at the entered position");
        }

        if (!canMovePiece(piece, newPositionPoint)) {
            //This exception should never be thrown since canMovePiece() should throw exceptions movement errors.
            throw new InvalidMoveException("The move is not allowed");
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
        if (!getBoard().positionWithinBoardBounds(newPosition)) {
            throw new InvalidMoveException("New position is outside board bounds");
        }

        if (piece.getColor() != getCurrentMovingPieceColor()) {
            throw new InvalidMoveException("The piece at the entered position can't be moved by you. It has wrong color.");
        }

        if (!piece.canMoveTo(newPosition)) {
            throw new InvalidMoveException("This piece is not allowed to move to the entered position");
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
