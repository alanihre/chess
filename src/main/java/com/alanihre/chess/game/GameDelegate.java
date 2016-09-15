package com.alanihre.chess.game;

import com.alanihre.chess.piece.Piece;

public interface GameDelegate {

    void gameReady(Game game);

    void requestMove(Piece.PieceColor color);

    void pieceCaptured(Piece piece, String coordinate);

    void pieceMoved(Piece piece, String coordinate);
    //void gameEnded(Reason); //TODO: Implement

}
