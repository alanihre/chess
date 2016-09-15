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
    public void testMovementConstraints() {
        //Test that the knight can move two steps forward and one step aside, in any direction.

        Point newPosition1 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 1);
        assertTrue("Two steps right, one step forward", knight.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 1);
        assertTrue("Two steps right, one step backward", knight.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 1);
        assertTrue("Two steps left, one step forward", knight.canMoveTo(newPosition3));

        Point newPosition4 = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 1);
        assertTrue("Two steps left, one step backward", knight.canMoveTo(newPosition4));

        Point newPosition5 = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 2);
        assertTrue("Two steps forward, one step right", knight.canMoveTo(newPosition5));

        Point newPosition6 = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y + 2);
        assertTrue("Two steps forward, one step left", knight.canMoveTo(newPosition6));

        Point newPosition7 = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y - 2);
        assertTrue("Two steps backward, one step right", knight.canMoveTo(newPosition7));

        Point newPosition8 = new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 2);
        assertTrue("Two steps backward, one step left", knight.canMoveTo(newPosition8));
    }

    @Test
    public void testCanLeap() {
        assertTrue(knight.canLeap());
    }

    //TODO: Add test for getMovementPathToPosition()
}
