package com.alanihre.chess.game;

import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

public class StubGameDelegate implements GameDelegate {

  public void gameReady(Game game) {

  }

  public void requestMove(Piece.PieceColor color) {

  }

  public void pieceCaptured(Piece piece, String coordinate) {

  }

  public void pieceMoved(Piece piece, String coordinate) {

  }

  public PieceType requestNewPieceOfType(PieceType[] availableTypes, String message) {
    return null;
  }

  public void gameEnded(String message) {

  }
}
