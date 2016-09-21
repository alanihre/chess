package com.alanihre.chessui;

import com.alanihre.chess.game.ClassicChessGame;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIChessApplication application = new UIChessApplication();
                application.createAndShowGUI();
                GameDelegate delegate = new UIChess(application);
                Game game = new ClassicChessGame(delegate);
            }
        });
    }
}
