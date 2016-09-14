package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private Point position;
    private PieceColor color;
    private int numberOfMoves = 0;

    Piece(Point position, PieceColor color) {
        this.position = position;
        this.color = color;
    }

    public abstract boolean canMoveTo(Point newPosition);

    public abstract char getSymbol();

    public PieceColor getColor() {
        return color;
    }

    public Point getPosition() {
        return position;
    }

    int getNumberOfMoves() {
        return numberOfMoves;
    }

    void increaseNumberOfMoves() {
        numberOfMoves++;
    }

    public boolean canLeap() {
        return false;
    }

    public void moveTo(Point position) {
        this.increaseNumberOfMoves();
        this.position = position;
    }

    //Calculates movement path for pieces moving any number of steps in one direction or diagonally only..
    //For other movement patterns please override.
    public List<Point> getMovementPathToPosition(Point newPosition) {
        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        if (positionXDelta == positionYDelta) {
            return calculateDiangoalMovementPath(newPosition);
        } else {
            return calculateSingleDirectionMovementPath(newPosition);
        }
    }

    private List<Point> calculateSingleDirectionMovementPath(Point newPosition) {
        List<Point> movementPath = new ArrayList<Point>();

        Point piecePosition = getPosition();
        int piecePositionX = piecePosition.getX();
        int piecePositionY = piecePosition.getY();
        int positionXDelta = newPosition.getX() - piecePositionX;
        int positionYDelta = newPosition.getY() - piecePositionY;

        if (positionXDelta == 0) {
            int deltaSign = Integer.signum(positionYDelta);

            for (int i = 0; i < Math.abs(positionYDelta); i++) {
                int newPiecePositionY = piecePositionY + deltaSign * (i + 1);
                Point point = new Point(piecePositionX, newPiecePositionY);
                movementPath.add(point);
            }
        } else {
            int deltaSign = Integer.signum(positionXDelta);

            for (int i = 0; i < Math.abs(positionXDelta); i++) {
                int newPiecePositionX = piecePositionX + deltaSign * (i + 1);
                Point point = new Point(newPiecePositionX, piecePositionY);
                movementPath.add(point);
            }
        }

        return movementPath;
    }

    private List<Point> calculateDiangoalMovementPath(Point newPosition) {
        List<Point> movementPath = new ArrayList<Point>();

        Point piecePosition = getPosition();
        int piecePositionX = piecePosition.getX();
        int piecePositionY = piecePosition.getY();
        //Since movement is diagonal, delta is the same for X and Y.
        int positionDelta = newPosition.getX() - piecePosition.getX();
        int deltaSign = Integer.signum(positionDelta);

        for (int i = 0; i < Math.abs(positionDelta); i++) {
            int newPiecePositionX = piecePositionX + deltaSign * (i + 1);
            int newPiecePositionY = piecePositionY + deltaSign * (i + 1);
            Point point = new Point(newPiecePositionX, newPiecePositionY);
            movementPath.add(point);
        }

        return movementPath;
    }

    public enum PieceColor {
        BLACK, WHITE
    }
}
