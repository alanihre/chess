package com.alanihre.chessui;

import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

class UIChess implements GameDelegate {

    private UIChessApplication application;

    public UIChess(UIChessApplication application) {
        this.application = application;
    }

    public void gameReady(Game game) {
        application.setGame(game);
        application.drawBoard();
    }

    public void requestMove(Piece.PieceColor color) {

    }

    public void pieceCaptured(Piece piece, String coordinate) {

    }

    public void pieceMoved(Piece piece, String coordinate) {
        application.drawBoard();
    }

    public PieceType requestNewPieceOfType(PieceType[] availableTypes, String message) {
        return null;
    }

    public void gameEnded(String message) {
        System.out.println("Game ended");
    }
}
