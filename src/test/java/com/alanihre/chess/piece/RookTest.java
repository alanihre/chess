package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RookTest {

    private Rook rook;

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;

    @Before
    public void setUp() {
        rook = new Rook(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testMovementConstraints() {
        //Test that the rook can move any number of steps up, down, left and right

        Point newPosition1 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue("Forwards movement with two steps", rook.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertTrue("Backwards movement with two steps", rook.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertFalse("Movement in two directions is not allowed", rook.canMoveTo(newPosition3));

        Point newPosition4 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertTrue("Sideways right movement with two steps", rook.canMoveTo(newPosition4));

        Point newPosition5 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertTrue("Sideways left movement with two steps", rook.canMoveTo(newPosition5));
    }
}
