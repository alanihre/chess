package com.alanihre.chessui;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game.GamePlayException;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.Point;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JFrame;

public class UIChessApplication {

    private static final int WINDOW_SIZE_WIDTH = 500;
    private static final int WINDOW_SIZE_HEIGHT = 500;

    private JFrame frame;
    private Point sourcePoint;
    private Game game;

    void setGame(Game game) {
        this.game = game;
    }

    void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("TopLevelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(8, 8));

        Dimension windowSize = new Dimension(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
        frame.setPreferredSize(windowSize);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void drawBoard() {
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        Board board = game.getBoard();

        Dimension pieceSize = new Dimension(WINDOW_SIZE_WIDTH / board.getWidth(), WINDOW_SIZE_HEIGHT / board.getHeight());

        for (int i = 0; i < board.getWidth() ; i++) {
            for (int pieceIndex = 0; pieceIndex < board.getHeight(); pieceIndex++) {

                char pieceSymbol = ' ';

                Point point = new Point(pieceIndex, i);
                Piece piece = board.getPieceAtPosition(point);
                if (piece != null) {
                    pieceSymbol = symbolForPiece(piece);
                }

                UIPiece uiPiece = new UIPiece(piece, point, pieceSymbol, pieceSize);

                if ((i % 2 ^ pieceIndex % 2) != 0) {
                    uiPiece.setBackground(new Color(248, 213, 131));
                } else {
                    uiPiece.setBackground(new Color(237, 237, 237));
                }

                frame.getContentPane().add(uiPiece);
                int x = i * (int)pieceSize.getWidth();
                int y = pieceIndex * (int)pieceSize.getHeight();
                uiPiece.setBounds(x, y, x + uiPiece.getWidth(), y + uiPiece.getHeight());

                uiPiece.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        UIPiece uiPiece = (UIPiece)e.getComponent();
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
