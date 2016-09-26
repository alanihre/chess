package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class FourDirectionMovement implements PieceMovement {

  public Path getPath(Position origin, Position destination) {
    Offset offset = origin.getOffsetTo(destination);

    if (offset.isOnlyHorizontal()) {
      return getHorizontalPath(origin, destination);
    } else if (offset.isOnlyVertical()) {
      return getVerticalPath(origin, destination);
    }

    return null;
  }

  private Path getHorizontalPath(Position origin, Position target) {
    Path path = new Path();
    Offset offset = origin.getOffsetTo(target);

    int direction = Integer.signum(offset.getX());
    // We do not want to include our initial square
    int x = origin.getFile() + direction;
    for (; x != target.getFile(); x += direction) {
      Position node = new Position(x, origin.getRank());
      path.addNode(node);
    }

    return path;
  }

  private Path getVerticalPath(Position origin, Position target) {
    Path path = new Path();
    Offset offset = origin.getOffsetTo(target);

    int direction = Integer.signum(offset.getY());
    // We do not want to include our initial square
    int y = origin.getRank() + direction;

    for (; y != target.getRank(); y += direction) {
      Position node = new Position(origin.getFile(), y);
      path.addNode(node);
    }

    return path;
  }
}
