package com.alanihre.chessui;

import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

import javax.swing.*;

class UIChess implements GameDelegate {

    private UIBoard uiBoard;
    private JFrame window;

    public UIChess(UIBoard uiBoard, JFrame window) {
        this.uiBoard = uiBoard;
        this.window = window;
    }

    public void gameReady(Game game) {
        uiBoard.setGame(game);
        uiBoard.drawBoard();
    }

    public void requestMove(Piece.PieceColor color) {

    }

    public void pieceCaptured(Piece piece, String coordinate) {

    }

    public void pieceMoved(Piece piece, String coordinate) {
        uiBoard.drawBoard();
    }

    public PieceType requestNewPieceOfType(PieceType[] availablePieces, String message) {
        String[] availablePiecesStrings = new String[availablePieces.length];
        for (int i = 0; i < availablePieces.length; i++) {
            availablePiecesStrings[i] = availablePieces[i].toString();
        }
        return PieceType.valueOf(showPieceTypeSelection(message, availablePiecesStrings).toUpperCase());
    }

    public void gameEnded(String message) {
        JOptionPane.showMessageDialog(window, "Game ended");
    }

    private String showPieceTypeSelection(String message, String[] pieceTypes) {
        return (String) JOptionPane.showInputDialog(window, "Pick a new piece type",
                message, JOptionPane.QUESTION_MESSAGE, null,
                pieceTypes,
                pieceTypes[0]);
    }
}
