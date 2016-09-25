package com.alanihre.chess.game;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    private final GameDelegate delegate;
    private Board board;
    private Piece.PieceColor currentMovingPieceColor;
    private List<Move> moves = new ArrayList<Move>();

    public Game(GameDelegate delegate) {
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
        Position oldPositionPosition = getBoard().labeledPointToBoardPoint(oldPosition);
        Position newPositionPosition = getBoard().labeledPointToBoardPoint(newPosition);
        movePiece(oldPositionPosition, newPositionPosition);
    }

    public void movePiece(Position oldPosition, Position newPosition) {
        //Check if any movement was made at all
        if (oldPosition.equals(newPosition)) {
            throw new InvalidMoveException("Old and new position are equal");
        }

        Square square = getBoard().getSquareAtPosition(oldPosition);
        if (!square.hasPiece()) {
            throw new InvalidMoveException("There is no piece at the entered position");
        }

        if (!getBoard().positionWithinBoardBounds(newPosition)) {
            throw new InvalidMoveException("New position is outside board bounds");
        }

        if (square.getPiece().getColor() != getCurrentMovingPieceColor()) {
            throw new InvalidMoveException("The piece at the entered position can't be moved by you. It has wrong color.");
        }

        Square newSquare = getBoard().getSquareAtPosition(newPosition);

        if (!canMakeMove(square, newSquare)) {
            throw new InvalidMoveException("The move is not allowed");
        }

        //Check if piece at target position can be captured
        Piece targetPositionPiece = getBoard().getSquareAtPosition(newPosition).getPiece();
        if (targetPositionPiece != null && pieceCanCapturePiece(square.getPiece(), targetPositionPiece)) {
            capturePiece(targetPositionPiece);
        }

        willMakeMove(square, newSquare);

        addMove(new Move(square, newSquare));

        square.getPiece().moveTo(newSquare);

        pieceMoved(newSquare.getPiece(), oldPosition);

        String readableCoordinate = getBoard().boardPointToLabeledPoint(newSquare.getPosition());
        getDelegate().pieceMoved(newSquare.getPiece(), readableCoordinate);

        prepareForNextMove();
        getDelegate().requestMove(getCurrentMovingPieceColor());
    }

    protected void willMakeMove(Square oldSquare, Square newSquare) {

    }

    protected void addMove(Move move) {
        moves.add(move);
    }

    protected List<Move> getMoves() {
        return moves;
    }

    protected Move getLastMove() {
        if (moves.size() > 0) {
            return moves.get(moves.size() - 1);
        }
        return null;
    }

    protected void pieceMoved(Piece piece, Position oldPosition) {

    }

    protected void prepareForNextMove() {
        if (getCurrentMovingPieceColor() == Piece.PieceColor.BLACK) {
            setCurrentMovingPieceColor(Piece.PieceColor.WHITE);
        } else {
            setCurrentMovingPieceColor(Piece.PieceColor.BLACK);
        }
    }

    protected boolean canMakeMove(Square oldSquare, Square newSquare) {
        return oldSquare.getPiece().canMoveTo(newSquare);
    }

    protected boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece) {
        return targetPiece.getColor() != capturingPiece.getColor();
    }

    protected void capturePiece(Piece piece) {
        Square square = piece.getSquare();
        square.removePiece();
        String readableCoordinate = getBoard().boardPointToLabeledPoint(square.getPosition());
        getDelegate().pieceCaptured(piece, readableCoordinate);
    }
}
