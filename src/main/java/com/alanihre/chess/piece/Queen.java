package com.alanihre.chess.piece;

import com.alanihre.chess.piece.movement.AnyDirectionMovement;

public class Queen extends Piece {

  public Queen(PieceColor color) {
    super(PieceType.QUEEN, color);

    AnyDirectionMovement movement = new AnyDirectionMovement();
    setMovement(movement);
  }

  public String getPieceName() {
    return "queen";
  }

  public char getSymbol() {
    if (getColor() == PieceColor.WHITE) {
      return '♕';
    } else {
      return '♛';
    }
  }
}
