package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BishopTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Bishop bishop;

    @Before
    public void setUp() {
        bishop = new Bishop(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testMovementConstraints() {
        //Test that the bishop can move diagonally

        Point newPosition1 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertTrue("South-east diagonal movement with two steps", bishop.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 2);
        assertTrue("South-west diagonal movement with two steps", bishop.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        assertTrue("North-east diagonal movement with two steps", bishop.canMoveTo(newPosition3));

        Point newPosition4 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        assertTrue("North-west movement with two steps", bishop.canMoveTo(newPosition4));

        Point newPosition5 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertFalse("Non-diagonal movement not allowed", bishop.canMoveTo(newPosition5));
    }
}
