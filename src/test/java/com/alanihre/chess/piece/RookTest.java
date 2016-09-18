package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RookTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Rook rook;

    @Before
    public void setUp() {
        rook = new Rook(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testLegalMoveForward() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue(rook.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveBackward() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertTrue(rook.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveInTwoDirections() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertFalse(rook.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertTrue(rook.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertTrue(rook.canMoveTo(newPosition));
    }
}
