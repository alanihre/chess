package com.alanihre.chessui;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.Position;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GamePlayException;
import com.alanihre.chess.piece.Piece;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

class UIBoard extends JComponent {

    private Position sourcePosition;
    private Game game;

    UIBoard() {
        super();

        setLayout(new GridLayout());
    }

    void setGame(Game game) {
        this.game = game;

        Board board = game.getBoard();
        setLayout(new GridLayout(board.getWidth() + 1, board.getHeight() + 1));
    }

    void drawBoard() {
        removeAll();
        revalidate();
        repaint();

        Board board = game.getBoard();

        Dimension pieceSize = new Dimension(getWidth() / (board.getWidth() + 2), getHeight() / (board.getHeight() + 2));

        char[] verticalLabels = board.getVerticalLabels();

        for (int i = 0; i < board.getWidth(); i++) {
            Label currentVerticalLabel = new Label(String.valueOf(verticalLabels[i]).toUpperCase());
            currentVerticalLabel.setSize(pieceSize);
            add(currentVerticalLabel);
            for (int pieceIndex = 0; pieceIndex < board.getHeight(); pieceIndex++) {

                Position position = new Position(pieceIndex, i);
                Piece piece = board.getSquareAtPosition(position).getPiece();

                UIPiece uiPiece = new UIPiece(piece, position, pieceSize);

                if ((i % 2 ^ pieceIndex % 2) != 0) {
                    uiPiece.setBackground(new Color(248, 213, 131));
                } else {
                    uiPiece.setBackground(new Color(237, 237, 237));
                }

                add(uiPiece);

                uiPiece.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        UIPiece uiPiece = (UIPiece) e.getComponent();
                        Position position = uiPiece.getPosition();
                        if (sourcePosition == null) {
                            sourcePosition = position;
                        } else {
                            move(sourcePosition, position);
                            sourcePosition = null;
                        }

                    }
                });
            }
        }

        Label blankLabel = new Label();
        blankLabel.setSize(pieceSize);
        add(blankLabel);

        char[] horizontalLabels = board.getHorizontalLabels();
        for (int i = 0; i < board.getWidth(); i++) {
            Label currentHorizontalLabel = new Label(String.valueOf(horizontalLabels[i]));
            currentHorizontalLabel.setSize(pieceSize);
            add(currentHorizontalLabel);
        }

    }

    private void move(Position sourcePosition, Position destinationPosition) {
        try {
            game.movePiece(sourcePosition, destinationPosition);
        } catch (GamePlayException exception) {
            //System.out.println(exception.getMessage());
            //System.out.println("Please try again.");
            //requestMove(color);
        }
    }
}
