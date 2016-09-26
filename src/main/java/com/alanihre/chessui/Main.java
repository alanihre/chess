package com.alanihre.chessui;

import com.alanihre.chess.game.ClassicChessGame;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.createAndShowGUI();

                UIBoard uiBoard = new UIBoard();
                uiBoard.setSize(window.getSize());
                window.getContentPane().add(uiBoard);

                GameDelegate delegate = new UIChess(uiBoard, window);
                Game game = new ClassicChessGame(delegate);
            }
        });
    }
}
