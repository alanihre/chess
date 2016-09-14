package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Point position, PieceColor color) {
        super(position, color);
    }

    public char getSymbol() {
        if (getColor() == PieceColor.WHITE) {
            return '♞';
        } else {
            return '♘';
        }
    }

    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean canLeap() {
        return true;
    }

    public boolean canMoveTo(Point newPosition) {
        //Knights can move two steps forward and one step aside in all directions.

        Point piecePosition = getPosition();
        int positionXDelta = newPosition.getX() - piecePosition.getX();
        int positionYDelta = newPosition.getY() - piecePosition.getY();

        return (positionXDelta == 2 && positionYDelta == 1) || (positionXDelta == 1 && positionYDelta == 2);
    }

    //TODO: Not necessary since knights can leap??
    @Override
    public List<Point> getMovementPathToPosition(Point newPosition) {
        List<Point> movementPath = new ArrayList<Point>();

        Point piecePosition = getPosition();
        int piecePositionX = piecePosition.getX();
        int piecePositionY = piecePosition.getY();
        int positionXDelta = newPosition.getX() - piecePositionX;
        int positionYDelta = newPosition.getY() - piecePositionY;
        int deltaXSign = Integer.signum(positionYDelta);
        int deltaYSign = Integer.signum(positionYDelta);

        if (Math.abs(positionXDelta) == 2) {
            Point point1 = new Point(piecePositionX + deltaXSign, piecePositionY);
            movementPath.add(point1);

            Point point2 = new Point(piecePositionX + deltaXSign * 2, piecePositionY);
            movementPath.add(point2);

            Point point3 = new Point(point2.getX(), piecePositionY + deltaYSign);
            movementPath.add(point3);

        } else {
            Point point1 = new Point(piecePositionX, piecePositionY + deltaYSign);
            movementPath.add(point1);

            Point point2 = new Point(piecePositionX, piecePositionY + deltaYSign * 2);
            movementPath.add(point2);

            Point point3 = new Point(piecePositionX + deltaXSign, point2.getY());
            movementPath.add(point3);
        }

        return movementPath;
    }
}
