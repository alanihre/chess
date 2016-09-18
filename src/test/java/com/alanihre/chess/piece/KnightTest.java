package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class KnightTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Knight knight;

    @Before
    public void setUp() {
        knight = new Knight(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testLegalMoveRightForward() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 1);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveRightBackward() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 1);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveLeftForward() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 1);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveLeftBackward() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 1);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveForwardRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 2);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveForwardLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y + 2);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveBackwardRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y - 2);
        assertTrue(knight.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveBackwardLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 2);
        assertTrue(knight.canMoveTo(newPosition));
    }

    //TODO: Test illegal moves

    @Test
    public void testCanLeap() {
        assertTrue(knight.canLeap());
    }

    //TODO: Add test for getMovementPathToPosition()
}
