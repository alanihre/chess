package com.alanihre.chess.game;

import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

public interface GameDelegate {

    void gameReady(Game game);

    void requestMove(Piece.PieceColor color);

    void pieceCaptured(Piece piece, String coordinate);

    void pieceMoved(Piece piece, String coordinate);

    PieceType requestNewPieceOfType(PieceType[] availableTypes, String message);

    //void gameEnded(Reason); //TODO: Implement

}
