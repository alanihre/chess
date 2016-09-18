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
    public void testLegalFirstMoveOneStepForward() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testLegalFirstMoveTwoSteps() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testLegalSecondMoveForwards() {
        //Simulate that this is not the firsts move of the pawn
        pawn.increaseNumberOfMoves();

        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalSecondMoveTwoStepsForward() {
        //Simulate that this is not the firsts move of the pawn
        pawn.increaseNumberOfMoves();

        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertFalse(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveSideways() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
        assertFalse(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveBackwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
        assertFalse(pawn.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalFirstMoveThreeStepsForward() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 3);
        assertFalse(pawn.canMoveTo(newPosition));
    }
}
