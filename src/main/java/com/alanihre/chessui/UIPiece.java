package com.alanihre.chessui;

import com.alanihre.chess.Point;
import com.alanihre.chess.piece.Piece;

import java.awt.*;

import javax.swing.*;

public class UIPiece extends JLabel {

    private final Piece piece;
    private final Point point;

    public UIPiece(Piece piece, Point point, Dimension dimension) {
        super();
        setSize(dimension);

        this.piece = piece;
        this.point = point;

        setupLabel();
    }

    Piece getPiece() {
        return piece;
    }

    Point getPoint() {
        return point;
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
