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

  private static final Color black = new Color(248, 213, 131);
  private static final Color white = new Color(237, 237, 237);
  private static final Color highlightSelected = new Color(120, 120, 230);

  UIBoard() {
    super();

    setLayout(new GridLayout());
  }

  void setGame(Game game) {
    this.game = game;

    Board board = game.getBoard();
    setLayout(new GridLayout(board.getWidth() + 2, board.getHeight() + 2));
  }

  void drawBoard() {
    removeAll();
    revalidate();
    repaint();

    Board board = game.getBoard();

    Dimension pieceSize = new Dimension(getWidth() / (board.getWidth() + 2), getHeight() / (board.getHeight() + 2));

    addLabel(null, pieceSize);

    char[] horizontalLabels = board.getHorizontalLabels();
    for (int i = 0; i < board.getWidth(); i++) {
      addLabel(String.valueOf(horizontalLabels[i]), pieceSize);
    }

    addLabel(null, pieceSize);

    char[] verticalLabels = board.getVerticalLabels();

    for (int i = 0; i < board.getWidth(); i++) {
      addLabel(String.valueOf(verticalLabels[i]), pieceSize);

      for (int pieceIndex = 0; pieceIndex < board.getHeight(); pieceIndex++) {

        Position position = new Position(pieceIndex, i);
        Piece piece = board.getSquareAtPosition(position).getPiece();

        UIPiece uiPiece = new UIPiece(piece, position, pieceSize);

        uiPiece.setBackground(colorAtPosition(i, pieceIndex));
        if (sourcePosition != null) {
          if (uiPiece.getPosition().equals(sourcePosition)) {
            uiPiece.setBackground(highlightSelected);
          }
        }

        final UIBoard uiBoard = this;
        
        add(uiPiece);
        uiPiece.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            UIPiece uiPiece = (UIPiece) e.getComponent();
            Position position = uiPiece.getPosition();
            if (sourcePosition == null) {
              if (uiPiece.getPiece() == null) {
                return;
              }
              sourcePosition = position;
            } else {
              move(sourcePosition, position);
              sourcePosition = null;
            }
            uiBoard.drawBoard();
          }
        });
      }

      addLabel(String.valueOf(verticalLabels[i]), pieceSize);
    }

    addLabel(null, pieceSize);

    for (int i = 0; i < board.getWidth(); i++) {
      addLabel(String.valueOf(horizontalLabels[i]), pieceSize);
    }

    addLabel(null, pieceSize);
  }

  private void addLabel(String text, Dimension size) {
    JLabel label = new JLabel(text);
    label.setSize(size);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setVerticalAlignment(SwingConstants.CENTER);
    add(label);
  }

  private void move(Position sourcePosition, Position destinationPosition) {
    try {
      game.movePiece(sourcePosition, destinationPosition);
    } catch (GamePlayException exception) {
      JOptionPane.showMessageDialog(this, exception.getMessage());
      this.sourcePosition = null;
    }
  }

  /**
   * Standard chess tile color at the given position.
   */
  private Color colorAtPosition(int x, int y) {
    if (((x + y) % 2) != 0) {
      return black;
    } else {
      return white;
    }
  }
}
