package com.alanihre.chess.game;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.StubBoard;

public class StubGame extends Game {

    private static int BOARD_SIZE_WIDTH = 8;
    private static int BOARD_SIZE_HEIGHT = 9;
    public StubGame(GameDelegate delegate) {
        super(delegate);
    }

    protected Board initializeBoard() {
        return new StubBoard(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
    }
}
