package com.alanihre.chess;

import com.alanihre.chess.game.ClassicChessGame;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;
import com.alanihre.chess.interfaces.TerminalGameInterface;

public class Main {
    public static void main(String[] args) {
        GameDelegate ioInterface = new TerminalGameInterface();
        Game game = new ClassicChessGame(ioInterface);
    }
}
