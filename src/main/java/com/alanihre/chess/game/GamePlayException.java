package com.alanihre.chess.game;

public class GamePlayException extends RuntimeException {
  public GamePlayException() {
    super();
  }

  public GamePlayException(String message) {
    super(message);
  }
}