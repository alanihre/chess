package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class KnightMovement implements PieceMovement {

  public Path getPath(Position origin, Position destination) {

    Offset offset = origin.getOffsetTo(destination);
    //Knights can move two steps forward and one step aside in all directions.
    int offsetXDelta = Math.abs(offset.getX());
    int offsetYDelta = Math.abs(offset.getY());
    if ((offsetXDelta == 2 && offsetYDelta == 1) || (offsetXDelta == 1 && offsetYDelta == 2)) {
      return getKnightPath(origin, destination);
    }

    return null;
  }

  private Path getKnightPath(Position origin, Position target) {
    Path path = new Path();
    Offset offset = origin.getOffsetTo(target);

    int piecePositionFile = origin.getFile();
    int piecePositionRank = origin.getRank();
    int deltaXSign = Integer.signum(offset.getX());
    int deltaYSign = Integer.signum(offset.getY());

    if (Math.abs(offset.getX()) == 2) {
      Position position1 = new Position(piecePositionFile + deltaXSign, piecePositionRank);
      path.addNode(position1);

      Position position2 = new Position(piecePositionFile + deltaXSign * 2, piecePositionRank);
      path.addNode(position2);
    } else {
      Position position1 = new Position(piecePositionFile, piecePositionRank + deltaYSign);
      path.addNode(position1);

      Position position2 = new Position(piecePositionFile, piecePositionRank + deltaYSign * 2);
      path.addNode(position2);
    }

    return path;
  }
}
