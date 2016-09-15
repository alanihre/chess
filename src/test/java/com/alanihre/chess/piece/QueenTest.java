package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class QueenTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Queen queen;

    @Before
    public void setUp() {
        queen = new Queen(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testMovementConstraints() {
        //Test that the queen can move both diagonally, up, down, left and right

        Point newPosition1 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertTrue("South-east diagonal movement with two steps", queen.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 2);
        assertTrue("South-west diagonal movement with two steps", queen.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        assertTrue("North-east diagonal movement with two steps", queen.canMoveTo(newPosition3));

        Point newPosition4 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        assertTrue("North-west movement with two steps", queen.canMoveTo(newPosition4));

        Point newPosition5 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue("Forwards movement with two steps", queen.canMoveTo(newPosition5));

        Point newPosition6 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertTrue("Backwards movement with two steps", queen.canMoveTo(newPosition6));

        Point newPosition7 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertTrue("Sideways right movement with two steps", queen.canMoveTo(newPosition7));

        Point newPosition8 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertTrue("Sideways left movement with two steps", queen.canMoveTo(newPosition8));
    }
}
