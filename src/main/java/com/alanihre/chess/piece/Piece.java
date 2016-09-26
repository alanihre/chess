package com.alanihre.chess.piece;

import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.movement.PieceMovement;

import java.util.List;

public abstract class Piece {

  private final PieceType type;
  private Square square;
  private PieceColor color;
  private int numberOfMoves = 0;
  private PieceMovement movement;

  Piece(PieceType type, PieceColor color) {
    this.type = type;
    this.color = color;
  }

  public abstract String getPieceName();

  public abstract char getSymbol();

  public PieceColor getColor() {
    return color;
  }

  public PieceType getType() {
    return type;
  }

  public Square getSquare() {
    return square;
  }

  public void setSquare(Square square) {
    this.square = square;
  }

  public int getNumberOfMoves() {
    return numberOfMoves;
  }

  public PieceMovement getMovement() {
    return movement;
  }

  public void setMovement(PieceMovement movement) {
    this.movement = movement;
  }

  void increaseNumberOfMoves() {
    numberOfMoves++;
  }

  public boolean canLeap() {
    return false;
  }

  public boolean canCapturePieceAt(Square target) {
    return target.getPiece().getColor() != getColor();
  }

  public void moveTo(Square destination) {
    this.increaseNumberOfMoves();

    square.removePiece();
    destination.setPiece(this);
    square = destination;
  }

  public boolean canMoveTo(Square destination) {
    Path path = movement.getPath(square.getPosition(), destination.getPosition());
    if (path == null) {
      return false;
    }
    List<Piece> piecesInPath = square.getBoard().getPiecesInPath(path);
    return piecesInPath.isEmpty() || canLeap();
  }

  public enum PieceColor {
    BLACK, WHITE;

    public static PieceColor invert(PieceColor color) {
      if (color == WHITE) {
        return BLACK;
      }

      return WHITE;
    }
  }
}