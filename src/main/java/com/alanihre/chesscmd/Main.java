package com.alanihre.chesscmd;

import com.alanihre.chess.game.ClassicChessGame;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;

public class Main {
    public static void main(String[] args) {
        GameDelegate delegate = new TerminalChess();
        Game game = new ClassicChessGame(delegate);
    }
}
