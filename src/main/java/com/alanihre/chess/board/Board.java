package com.alanihre.chess.board;

import com.alanihre.chess.game.GamePlayException;
import com.alanihre.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {

    private final int width;
    private final int height;
    private final char[] horizontalLabels;
    private final char[] verticalLabels;
    private Square[][] squares;

    protected Board(int width, int height) {
        this.width = width;
        this.height = height;

        createSquares();

        horizontalLabels = createHorizontalLabels();
        verticalLabels = createVerticalLabels();
    }

    public abstract void setupBoard();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private char[] createHorizontalLabels() {
        char[] horizontalLabels = new char[width];
        for (int i = 0; i < width; i++) {
            //97 is unicode value for 'a'
            horizontalLabels[i] = (char) (97 + i);
        }
        return horizontalLabels;
    }

    private char[] createVerticalLabels() {
        char[] verticalLabels = new char[height];
        //Labels are in reverse order since vertical chess labels start from the highest number from the top
        for (int i = height; i > 0; i--) {
            verticalLabels[height - i] = Character.forDigit(i, 10);
        }
        return verticalLabels;
    }

    private void createSquares() {
        squares = new Square[width][height];
        for (int file = 0; file < getWidth(); file++) {
            for (int rank = 0; rank < getHeight(); rank++) {
                Position position = new Position(file, rank);
                Square square = new Square(this, position);
                squares[file][rank] = square;
            }
        }
    }

    public char[] getHorizontalLabels() {
        return horizontalLabels;
    }

    public char[] getVerticalLabels() {
        return verticalLabels;
    }

    public Square getSquareAtPosition(Position position) {
        return squares[position.getFile()][position.getRank()];
    }

    public void putPiece(Piece piece, Position position) throws GamePlayException {
        Square square = getSquareAtPosition(position);

        if (square.hasPiece()) {
            throw new GamePlayException("A piece is already occupying square at " + square.getPosition());
        }

        square.setPiece(piece);
    }

    public boolean positionWithinBoardBounds(Position position) {
        return position.getFile() < getWidth()
                && position.getFile() >= 0
                && position.getRank() < getHeight()
                && position.getRank() >= 0;
    }

    public String boardPointToLabeledPoint(Position position) {
        String xLabel = String.valueOf(horizontalLabels[position.getFile()]);
        String yLabel = String.valueOf(verticalLabels[position.getRank()]);
        return xLabel + yLabel;
    }

    public Position labeledPointToBoardPoint(String labeledPoint) {
        char xLabel = labeledPoint.charAt(0);
        char yLabel = labeledPoint.charAt(1);
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

        return new Position(pointX, pointY);
    }

    public List<Piece> getPiecesInPath(Path path) {
        List<Piece> pieces = new ArrayList<Piece>();

        for (Position node : path.getNodes()) {
            Square squareAtPosition = getSquareAtPosition(node);
            if (squareAtPosition.hasPiece()) {
                Piece pieceInSquare = squareAtPosition.getPiece();
                pieces.add(pieceInSquare);
            }
        }

        return pieces;
    }
}
