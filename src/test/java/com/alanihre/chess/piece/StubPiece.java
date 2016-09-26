package com.alanihre.chess.piece;

public class StubPiece extends Piece {

  public StubPiece(PieceType type, PieceColor color) {
    super(type, color);
  }

  public String getPieceName() {
    return "testpiece";
  }

  public char getSymbol() {
    return 'a';
  }

}
