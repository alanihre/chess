package com.alanihre.chessui;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GamePlayException;
import com.alanihre.chess.piece.Piece;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

class UIBoard extends JComponent {

    private Point sourcePoint;
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

                Point point = new Point(pieceIndex, i);
                Piece piece = board.getPieceAtPosition(point);

                UIPiece uiPiece = new UIPiece(piece, point, pieceSize);

                if ((i % 2 ^ pieceIndex % 2) != 0) {
                    uiPiece.setBackground(new Color(248, 213, 131));
                } else {
                    uiPiece.setBackground(new Color(237, 237, 237));
                }

                add(uiPiece);

                uiPiece.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        UIPiece uiPiece = (UIPiece) e.getComponent();
                        Point point = uiPiece.getPoint();
                        if (sourcePoint == null) {
                            sourcePoint = point;
                        } else {
                            move(sourcePoint, point);
                            sourcePoint = null;
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

    private void move(Point sourcePoint, Point destinationPoint) {
        try {
            game.movePiece(sourcePoint, destinationPoint);
        } catch (GamePlayException exception) {
            //System.out.println(exception.getMessage());
            //System.out.println("Please try again.");
            //requestMove(color);
        }
    }
}
