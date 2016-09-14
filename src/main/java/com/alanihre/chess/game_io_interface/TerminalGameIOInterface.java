package com.alanihre.chess.game_io_interface;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.piece.Piece;

import java.util.Scanner;

public class TerminalGameIOInterface implements GameIOInterface {

    private Game game;
    private Scanner scanner;

    public TerminalGameIOInterface() {
        scanner = new Scanner(System.in);
    }

    public void gameReady(Game game) {
        this.game = game;
        System.out.println("Game is ready!");
        printBoard();
    }

    public void requestMove(Piece.PieceColor color) {
        String colorString;
        if (color == Piece.PieceColor.BLACK) {
            colorString = "black's";
        } else {
            colorString = "white's";
        }
        System.out.println("Please make next move. It is " + colorString + " turn.");
        System.out.print("Enter source position: ");
        String sourcePosition = scanner.next();
        System.out.print("Enter target position: ");
        String targetPosition = scanner.next();

        game.movePiece(sourcePosition, targetPosition);
    }

    public void pieceCaptured(Piece piece, String coordinate) {
        String pieceName = piece.getPieceName();
        String pieceNameCapitalized = pieceName.substring(0, 1).toUpperCase() + pieceName.substring(1);
        System.out.println(pieceNameCapitalized + " captured at " + coordinate);
    }

    public void pieceMoved(Piece piece, String coordinate) {
        String pieceName = piece.getPieceName();
        String pieceNameCapitalized = pieceName.substring(0, 1).toUpperCase() + pieceName.substring(1);
        System.out.println(pieceNameCapitalized + " moved to " + coordinate);
        printBoard();
    }

    public void printBoard() {
        System.out.print(' ');

        Board board = game.getBoard();

        for (char label : board.getHorizontalLabels()) {
            System.out.print(' ');
            System.out.print(label);
        }

        for (int i = 0; i < board.getWidth(); i++) {
            System.out.println();

            char label = board.getVerticalLabels()[i];
            System.out.print(label);

            for (int pieceIndex = 0; pieceIndex < board.getHeight(); pieceIndex++) {
                System.out.print(' ');

                Piece piece = board.getPieceAtPosition(new Point(pieceIndex, i));
                if (piece != null) {
                    char pieceSymbol = piece.getSymbol();
                    System.out.print(pieceSymbol);
                } else {
                    System.out.print(' ');
                }
            }
        }

        System.out.println();
        System.out.println();
    }
}