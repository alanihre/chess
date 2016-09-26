package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Direction;
import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class SingleDirectionMovement extends FourDirectionMovement {
  private Direction direction;

  public SingleDirectionMovement(Direction direction) {
    this.direction = direction;
  }

  /* invertVerticalDirection should be used for pieces which vertical movement are
 inverted (i.e. pieces that originates from the top of the board).
  */
  public void invertVerticalDirection() {
    this.direction = Direction.invert(direction);
  }

  public Path getPath(Position origin, Position destination) {
    Offset offset = origin.getOffsetTo(destination);

    switch (this.direction) {
      case FORWARD:
        if (!offset.isOnlyVertical() || !offset.isPositiveVertical())
          return null;
        break;
      case BACKWARD:
        if (!offset.isOnlyVertical() || offset.isPositiveVertical())
          return null;
        break;
      case RIGHT:
        if (!offset.isOnlyHorizontal() || !offset.isPositiveHorizontal())
          return null;
        break;
      case LEFT:
        if (!offset.isOnlyHorizontal() || offset.isPositiveHorizontal())
          return null;
        break;
      default:
        return null;
    }

    return super.getPath(origin, destination);
  }
}
