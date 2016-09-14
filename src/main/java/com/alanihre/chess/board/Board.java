package com.alanihre.chess.board;

import com.alanihre.chess.Point;
import com.alanihre.chess.piece.Piece;

public abstract class Board {

    protected final int width;
    protected final int height;
    private final char[] horizontalLabels;
    private final char[] verticalLabels;
    protected Piece[][] squares;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        squares = new Piece[width][height];

        horizontalLabels = new char[width];
        for (int i = 0; i < width; i++) {
            //97 is unicode value for 'a'
            horizontalLabels[i] = (char) (97 + i);
        }

        verticalLabels = new char[height];
        //Labels are in reverse order since vertical chess labels start from the highest number from the top
        //TODO: Optimize
        for (int i = height - 1; i >= 0; i--) {
            verticalLabels[height - 1 - i] = Character.forDigit(i + 1, 10);
        }

    }

    public abstract void setupBoard();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[] getHorizontalLabels() {
        return horizontalLabels;
    }

    public char[] getVerticalLabels() {
        return verticalLabels;
    }

    public void addPiece(Piece piece) {
        int xPosition = piece.getPosition().getX();
        int yPosition = piece.getPosition().getY();
        squares[xPosition][yPosition] = piece;
    }

    public void removePiece(Piece piece) {
        int xPosition = piece.getPosition().getX();
        int yPosition = piece.getPosition().getY();
        squares[xPosition][yPosition] = null;
    }

    public Piece getPieceAtPosition(Point position) {
        return squares[position.getX()][position.getY()];
    }

    public boolean positionWithinBoardBounds(Point position) {
        return position.getX() <= getWidth()
                && position.getX() > 0
                && position.getY() <= getHeight()
                && position.getY() > 0;
    }

    public String boardPointToReadableCoordinate(Point point) {
        String xLabel = String.valueOf(horizontalLabels[point.getX()]);
        String yLabel = String.valueOf(verticalLabels[point.getY()]);
        return xLabel + yLabel;
    }

    public Point readableCoordinateToPoint(String coordinate) {
        char xLabel = coordinate.charAt(0);
        char yLabel = coordinate.charAt(1);
        int pointX = -1;
        for (int i = 0; i < horizontalLabels.length; i++) {
            if (horizontalLabels[i] == xLabel) {
                pointX = i;
                break;
            }
        }

        int pointY = -1;
        for (int i = 0; i < verticalLabels.length; i++) {
            if (verticalLabels[i] == yLabel) {
                pointY = i;
                break;
            }
        }

        return new Point(pointX, pointY);
    }
}
