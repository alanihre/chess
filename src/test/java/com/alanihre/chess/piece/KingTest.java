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
    public void testMovementConstraints() {
        //Test that the king can move only one step in any direction

        Point newPosition1 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue("Forwards movement with one step", king.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertFalse("Forwards movement with two steps (not allowed)", king.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
        assertTrue("Sideways right movement with one step", king.canMoveTo(newPosition3));

        Point newPosition4 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertFalse("Sideways right movement with two steps (not allowed)", king.canMoveTo(newPosition4));

        Point newPosition5 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
        assertTrue("Backwards movement with one step", king.canMoveTo(newPosition5));

        Point newPosition6 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertFalse("Backwards movement with two steps (not allowed)", king.canMoveTo(newPosition6));

        Point newPosition7 = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y);
        assertTrue("Left movement with one step", king.canMoveTo(newPosition7));

        Point newPosition8 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertFalse("Left movement with two steps (not allowed)", king.canMoveTo(newPosition8));
    }
}
