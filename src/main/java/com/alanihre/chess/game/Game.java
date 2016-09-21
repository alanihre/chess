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

        this.getDelegate().gameReady(this);
        getDelegate().requestMove(currentMovingPieceColor);
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
        movePiece(oldPositionPoint, newPositionPoint);
    }

    public void movePiece(Point oldPosition, Point newPosition) {
        //Check if any movement was made at all
        if (oldPosition.equals(newPosition)) {
            throw new InvalidMoveException("Old and new position are equal");
        }

        Piece piece = getBoard().getPieceAtPosition(oldPosition);
        if (piece == null) {
            throw new InvalidMoveException("There is no piece at the entered position");
        }

        if (!getBoard().positionWithinBoardBounds(newPosition)) {
            throw new InvalidMoveException("New position is outside board bounds");
        }

        if (piece.getColor() != getCurrentMovingPieceColor()) {
            throw new InvalidMoveException("The piece at the entered position can't be moved by you. It has wrong color.");
        }

        if (!canMovePiece(piece, newPosition)) {
            throw new InvalidMoveException("The move is not allowed");
        }

        //Check if piece at target position can be captured
        Piece targetPositionPiece = getBoard().getPieceAtPosition(newPosition);
        if (targetPositionPiece != null && pieceCanCapturePiece(piece, targetPositionPiece)) {
            capturePiece(targetPositionPiece);
        }

        willMovePiece(piece, newPosition);

        getBoard().removePiece(piece);
        piece.moveTo(newPosition);
        getBoard().putPiece(piece);

        pieceMoved(piece, oldPosition);

        String readableCoordinate = getBoard().boardPointToLabeledPoint(piece.getPosition());
        getDelegate().pieceMoved(piece, readableCoordinate);

        prepareForNextMove();
        getDelegate().requestMove(getCurrentMovingPieceColor());
    }

    abstract void willMovePiece(Piece piece, Point newPosition);

    abstract void pieceMoved(Piece piece, Point oldPosition);

    abstract void prepareForNextMove();

    protected boolean canMovePiece(Piece piece, Point newPosition) {
        if (!piece.canMoveTo(newPosition)) {
            return false;
        }

        return true;
    }

    abstract boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece);

    protected void capturePiece(Piece piece) {
        getBoard().removePiece(piece);
        String readableCoordinate = getBoard().boardPointToLabeledPoint(piece.getPosition());
        getDelegate().pieceCaptured(piece, readableCoordinate);
    }
}
