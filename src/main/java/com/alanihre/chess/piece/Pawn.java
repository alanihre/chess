package com.alanihre.chess.piece;

import com.alanihre.chess.board.Direction;
import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.movement.LimitedRangeMovement;
import com.alanihre.chess.piece.movement.PieceMovement;
import com.alanihre.chess.piece.movement.SingleDirectionMovement;

public class Pawn extends Piece {

  private static final int FIRST_MOVE_RANGE = 2;
  private static final int DEFAULT_MOVE_RANGE = 1;

  public Pawn(PieceColor color) {
    super(PieceType.PAWN, color);
    SingleDirectionMovement singleDirectionMovement = new SingleDirectionMovement(Direction.FORWARD);
    if (color == PieceColor.WHITE) {
      singleDirectionMovement.invertVerticalDirection();
    }
    PieceMovement movement = new LimitedRangeMovement(singleDirectionMovement, FIRST_MOVE_RANGE);
    setMovement(movement);
  }

  public String getPieceName() {
    return "pawn";
  }

  public char getSymbol() {
    if (getColor() == PieceColor.WHITE) {
      return '♙';
    } else {
      return '♟';
    }
  }

  @Override
  public void moveTo(Square destination) {
    super.moveTo(destination);
  }

  @Override
  public void increaseNumberOfMoves() {
    super.increaseNumberOfMoves();
    changeMovementRange();
  }

  @Override
  public boolean canMoveTo(Square destination) {
        /* Pawns can not, in difference from other pieces, capture pieces at their target position
        when they are moving in their normal movement pattern.
        They can move one step diagonally but only to capture a piece.
        */
    if (canCapturePieceAt(destination) || canMakePassantCapture(destination)) {
      return true;
    } else {
      if (super.canMoveTo(destination)
              && !getSquare().getBoard().getSquareAtPosition(destination.getPosition()).hasPiece()) {
        return true;
      }
    }

    return false;
  }

  private void changeMovementRange() {
    if (getNumberOfMoves() == 1) {
      LimitedRangeMovement movement = (LimitedRangeMovement) getMovement();
      movement.setMaxRange(DEFAULT_MOVE_RANGE);
    }
  }

  public boolean canCapturePieceAt(Square target) {
    Offset offset = getSquare().getPosition().getOffsetTo(target.getPosition());
    Piece.PieceColor pieceColor = getColor();
    Piece pieceAtTarget = getSquare().getBoard().getSquareAtPosition(target.getPosition()).getPiece();

    return pieceAtTarget != null
            && pieceAtTarget.getColor() != getColor()
            && Math.abs(offset.getX()) == Math.abs(offset.getY())
            && ((pieceColor == Piece.PieceColor.WHITE && offset.getY() == -1)
            || (pieceColor == Piece.PieceColor.BLACK && offset.getY() == 1));
  }

  public boolean canMakePassantCapture(Square target) {
    Offset offset = getSquare().getPosition().getOffsetTo(target.getPosition());
    Piece.PieceColor pieceColor = getColor();
    Piece passantPiece = getPassantPiece(target);

    return passantPiece != null
            && passantPiece.getNumberOfMoves() == 1
            && passantPiece.getColor() != getColor()
            && Math.abs(offset.getX()) == Math.abs(offset.getY())
            && ((pieceColor == Piece.PieceColor.WHITE && offset.getY() == -1)
            || (pieceColor == Piece.PieceColor.BLACK && offset.getY() == 1));
  }

  public Piece getPassantPiece(Square target) {
    int passantPieceRank = target.getPosition().getRank() + (getColor() == Piece.PieceColor.WHITE ? 1 : -1);
    Position passantPiecePosition = new Position(target.getPosition().getFile(), passantPieceRank);
    return getSquare().getBoard().getSquareAtPosition(passantPiecePosition).getPiece();
  }
}
