package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private King king;

    @Before
    public void setUp() {
        king = new King(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testLegalMoveForwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue(king.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveForwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertFalse(king.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
        assertTrue(king.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertFalse(king.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveBackwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
        assertTrue(king.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveBackwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertFalse(king.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y);
        assertTrue(king.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertFalse(king.canMoveTo(newPosition));
    }
}
