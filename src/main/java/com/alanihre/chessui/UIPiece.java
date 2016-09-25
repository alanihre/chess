package com.alanihre.chessui;

import com.alanihre.chess.board.Position;
import com.alanihre.chess.piece.Piece;

import java.awt.*;

import javax.swing.*;

public class UIPiece extends JLabel {

    private final Piece piece;
    private final Position position;

    public UIPiece(Piece piece, Position position, Dimension dimension) {
        super();
        setSize(dimension);

        this.piece = piece;
        this.position = position;

        setupLabel();
    }

    Piece getPiece() {
        return piece;
    }

    Position getPosition() {
        return position;
    }

    private void setupLabel() {
        setOpaque(true);
        if (piece != null) {
            setText(String.valueOf(piece.getSymbol()));
        }
        calculateFontSize();
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    private void calculateFontSize() {
        Font labelFont = getFont();
        String labelText = getText();

        int stringWidth = getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = getWidth();

        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = getHeight();

        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

}
