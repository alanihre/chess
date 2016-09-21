package com.alanihre.chessui;

import com.alanihre.chess.*;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.Point;

import java.awt.*;

import javax.swing.*;

public class UIPiece extends JLabel {

    private final Piece piece;
    private final char pieceSymbol;
    private final Point point;

    public UIPiece(Piece piece, Point point, char pieceSymbol, Dimension dimension) {
        super();
        setSize(dimension);

        this.piece = piece;
        this.point = point;
        this.pieceSymbol = pieceSymbol;

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
        setText(String.valueOf(pieceSymbol));
        setFont(new Font(getFont().getName(), Font.PLAIN, 40));
        calculateFontSize();
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    private void calculateFontSize() {
        Font labelFont = getFont();
        String labelText = getText();

        int stringWidth = getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

}
