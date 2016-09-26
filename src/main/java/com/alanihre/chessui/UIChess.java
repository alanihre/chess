package com.alanihre.chessui;

import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

class UIChess implements GameDelegate {

    private UIBoard uiBoard;

    public UIChess(UIBoard uiBoard) {
        this.uiBoard = uiBoard;
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
        return PieceType.valueOf(uiBoard.showPieceTypeSelection(message, availablePiecesStrings).toUpperCase());
    }

    public void gameEnded(String message) {
        System.out.println("Game ended");
    }
}
