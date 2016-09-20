package com.alanihre.chess.game;

public class InvalidMoveException extends GamePlayException {
    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}