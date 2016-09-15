package com.alanihre.chesscmd;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GameDelegate;
import com.alanihre.chess.game.InvalidMoveException;
import com.alanihre.chess.piece.Piece;

import java.util.Scanner;

public class TerminalChess implements GameDelegate {

    private Game game;
    private Scanner scanner;

    public TerminalChess() {
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
        System.out.println("It is " + colorString + " turn.");
        System.out.print("Enter source position: ");
        String sourcePosition = scanner.next();
        System.out.print("Enter target position: ");
        String targetPosition = scanner.next();

        try {
            game.movePiece(sourcePosition, targetPosition);
        } catch (InvalidMoveException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Please try again.");
            requestMove(color);
        }
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

    private void printBoard() {
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
                    char pieceSymbol = symbolForPiece(piece);
                    System.out.print(pieceSymbol);
                } else {
                    System.out.print(' ');
                }
            }
        }

        System.out.println();
        System.out.println();
    }

    private char symbolForPiece(Piece piece) {
        switch (piece.getType()) {
            case BISHOP: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♝';
                    case BLACK:
                        return '♗';
                }
                break;
            }
            case KING: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♚';
                    case BLACK:
                        return '♔';
                }
            }
            case KNIGHT: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♞';
                    case BLACK:
                        return '♘';
                }
            }
            case PAWN: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♟';
                    case BLACK:
                        return '♙';
                }
            }
            case QUEEN: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♛';
                    case BLACK:
                        return '♕';
                }
            }
            case ROOK: {
                switch (piece.getColor()) {
                    case WHITE:
                        return '♜';
                    case BLACK:
                        return '♖';
                }
            }
        }

        return '!';
    }
}
