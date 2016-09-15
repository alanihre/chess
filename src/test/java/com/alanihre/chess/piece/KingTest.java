package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {

    private King king;

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static Point PIECE_START_POSITION = new Point(2, 2);

    @Before
    public void setUp() {
        king = new King(PIECE_START_POSITION, PIECE_COLOR);
    }

    @Test
    public void testMovementConstraints() {
        //Test that the king can move only one step in any direction

        Point newPosition1 = new Point(2, 3);
        assertTrue("Forwards movement with one step", king.canMoveTo(newPosition1));

        Point newPosition2 = new Point(2, 4);
        assertFalse("Forwards movement with two steps (not allowed)", king.canMoveTo(newPosition2));

        Point newPosition3 = new Point(3, 2);
        assertTrue("Sideways right movement with one step", king.canMoveTo(newPosition3));

        Point newPosition4 = new Point(4, 2);
        assertFalse("Sideways right movement with two steps (not allowed)", king.canMoveTo(newPosition4));

        Point newPosition5 = new Point(2, 1);
        assertTrue("Backwards movement with one step", king.canMoveTo(newPosition5));

        Point newPosition6 = new Point(2, 0);
        assertFalse("Backwards movement with two steps (not allowed)", king.canMoveTo(newPosition6));

        Point newPosition7 = new Point(1, 2);
        assertTrue("Left movement with one step", king.canMoveTo(newPosition7));

        Point newPosition8 = new Point(0, 2);
        assertFalse("Left movement with two steps (not allowed)", king.canMoveTo(newPosition8));
    }
}
