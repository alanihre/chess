package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PawnTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Pawn pawn;

    @Before
    public void setUp() {
        pawn = new Pawn(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
    }

    @Test
    public void testMovementConstraints() {
        //Test that the pawn can move one step forward

        //Simulate that this is not the firsts move of the pawn
        pawn.increaseNumberOfMoves();

        Point newPosition1 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue("Forwards movement with one step", pawn.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertFalse("Forwards movement with two steps not allowed", pawn.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
        assertFalse("Sideways movement not allowed", pawn.canMoveTo(newPosition3));

        Point newPosition6 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
        assertFalse("Backwards movement not allowed", pawn.canMoveTo(newPosition6));
    }

    @Test
    public void testFirstMove() {
        //Test that the pawn can move one or two steps forward on it's first move

        Point newPosition1 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue("Forwards movement with one step", pawn.canMoveTo(newPosition1));

        Point newPosition2 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue("Forwards movement with two steps", pawn.canMoveTo(newPosition2));

        Point newPosition3 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 3);
        assertFalse("Forwards movement with more than two steps is not allowed", pawn.canMoveTo(newPosition3));
    }
}
