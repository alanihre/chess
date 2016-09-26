package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class DiagonalMovement implements PieceMovement {

  public Path getPath(Position origin, Position destination) {
    Offset offset = origin.getOffsetTo(destination);

    if (offset.isDiagonal()) {
      return getDiagonalPath(origin, destination);
    }

    return null;
  }

  private Path getDiagonalPath(Position origin, Position target) {
    Path path = new Path();
    Offset offset = origin.getOffsetTo(target);

    int directionFile = Integer.signum(offset.getX());
    int directionRank = Integer.signum(offset.getY());

    //Since movement is diagonal, delta is the same size for X and Y.
    for (int i = 1; i < Math.abs(offset.getX()); i++) {
      int file = origin.getFile() + directionFile * i;
      int rank = origin.getRank() + directionRank * i;
      Position node = new Position(file, rank);
      path.addNode(node);

    }
    return path;
  }
}