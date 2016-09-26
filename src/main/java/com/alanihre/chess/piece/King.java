package com.alanihre.chess.piece;

import com.alanihre.chess.piece.movement.FourDirectionMovement;
import com.alanihre.chess.piece.movement.LimitedRangeMovement;
import com.alanihre.chess.piece.movement.PieceMovement;

public class King extends Piece {

  private static final int MOVE_RANGE = 1;

  public King(PieceColor color) {
    super(PieceType.KING, color);

    FourDirectionMovement fourDirectionMovement = new FourDirectionMovement();
    PieceMovement movement = new LimitedRangeMovement(fourDirectionMovement, MOVE_RANGE);
    setMovement(movement);
  }

  public String getPieceName() {
    return "king";
  }

  public char getSymbol() {
    if (getColor() == PieceColor.WHITE) {
      return '♔';
    } else {
      return '♚';
    }
  }
}
