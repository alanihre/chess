package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final PieceType type;
    private Point position;
    private PieceColor color;
    private int numberOfMoves = 0;

    Piece(PieceType type, Point position, PieceColor color) {
        this.type = type;
        this.position = position;
        this.color = color;
    }

    public abstract boolean canMoveTo(Point newPosition);

    public abstract String getPieceName();

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Point getPosition() {
        return position;
    }

    public int getNumberOfMoves() {
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

    //Calculates movement path for pieces moving any number of steps in one direction or diagonally only.
    //For other movement patterns please override.
    public List<Point> getMovementPathToPosition(Point newPosition) {
        Point piecePosition = getPosition();
        int positionXDelta = Math.abs(newPosition.getX() - piecePosition.getX());
        int positionYDelta = Math.abs(newPosition.getY() - piecePosition.getY());

        if (positionXDelta == positionYDelta) {
            return calculateDiagonalMovementPath(newPosition);
        } else if (positionXDelta == 0 || positionYDelta == 0) {
            return calculateSingleDirectionMovementPath(newPosition);
        } else {
            throw new PieceMovementNotSupportedException("Movement from " + getPosition().toString() + " to " + newPosition.toString() + " can not be handled by getMovementPathToPosition(). Please implement a custom method for this in your subclass of Piece.");
        }
    }

    List<Point> calculateSingleDirectionMovementPath(Point newPosition) {
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

    List<Point> calculateDiagonalMovementPath(Point newPosition) {
        List<Point> movementPath = new ArrayList<Point>();

        Point piecePosition = getPosition();
        int piecePositionX = piecePosition.getX();
        int piecePositionY = piecePosition.getY();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();
        int deltaSignX = Integer.signum(positionXDelta);
        int deltaSignY = Integer.signum(positionYDelta);

        //Since movement is diagonal, delta is the same size for X and Y.
        for (int i = 0; i < Math.abs(positionXDelta); i++) {
            int newPiecePositionX = piecePositionX + deltaSignX * (i + 1);
            int newPiecePositionY = piecePositionY + deltaSignY * (i + 1);
            Point point = new Point(newPiecePositionX, newPiecePositionY);
            movementPath.add(point);
        }

        return movementPath;
    }

    public enum PieceColor {
        BLACK, WHITE
    }
}