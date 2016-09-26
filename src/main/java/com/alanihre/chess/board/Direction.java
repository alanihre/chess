package com.alanihre.chess.board;

public enum Direction {
  FORWARD, BACKWARD, RIGHT, LEFT;

  public static Direction invert(Direction direction) {
    switch (direction) {
      case FORWARD:
        return BACKWARD;
      case BACKWARD:
        return FORWARD;
      case RIGHT:
        return LEFT;
      case LEFT:
        return RIGHT;
    }

    return null;
  }
}