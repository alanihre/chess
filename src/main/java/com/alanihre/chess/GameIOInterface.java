package com.alanihre.chess;

public interface GameIOInterface {

    void gameReady(Game game);
    void requestMove(Piece.PieceColor color);
    void pieceCaptured(Piece piece, String coordinate);
    void pieceMoved(Piece piece, String coordinate);
    //void gameEnded(Reason); //TODO: Implement
    //public void moveRejected(Reason); //TODO: Implement

}
