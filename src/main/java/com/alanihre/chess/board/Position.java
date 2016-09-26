package com.alanihre.chess.board;

public class Position {

  private final int file;
  private final int rank;

  public Position(int file, int rank) {
    this.file = file;
    this.rank = rank;
  }

  public int getFile() {
    return file;
  }

  public int getRank() {
    return rank;
  }

  public Offset getOffsetTo(Position other) {
    int xOffset = other.getFile() - file;
    int yOffset = other.getRank() - rank;

    return new Offset(xOffset, yOffset);
  }

  @Override
  public String toString() {
    return ("(" + file + "," + rank + ")");
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object == null) {
      return false;
    }

    if (getClass() != object.getClass()) {
      return false;
    }
    Position position = (Position) object;

    return position.getFile() == getFile() && position.getRank() == getRank();
  }
}
