package com.alanihre.chess.piece;

import com.alanihre.chess.piece.movement.DiagonalMovement;

public class Bishop extends Piece {

  public Bishop(PieceColor color) {
    super(PieceType.BISHOP, color);

    DiagonalMovement movement = new DiagonalMovement();
    setMovement(movement);
  }

  public String getPieceName() {
    return "bishop";
  }

  public char getSymbol() {
    if (getColor() == PieceColor.WHITE) {
      return '♗';
    } else {
      return '♝';

    }
  }
}
