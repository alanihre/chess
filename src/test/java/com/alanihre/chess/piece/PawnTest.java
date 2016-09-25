package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PawnTest extends PieceTestBase{

    @Before
    public void setUp() {
        piece = new Pawn(PIECE_COLOR);
        super.setUp();
    }

    @Test
    public void testLegalFirstMoveOneStepForward() {
        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testLegalFirstMoveTwoSteps() {
        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testLegalSecondMoveForwards() {
        //Simulate that this is not the firsts move of the pawn
        piece.increaseNumberOfMoves();

        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testIllegalSecondMoveTwoStepsForward() {
        //Simulate that this is not the firsts move of the pawn
        piece.increaseNumberOfMoves();

        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertFalse(canMoveToPosition(newPosition));
    }

    @Test
    public void testIllegalMoveSideways() {
        Position newPosition = new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
        assertFalse(canMoveToPosition(newPosition));
    }

    @Test
    public void testIllegalMoveBackwards() {
        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
        assertFalse(canMoveToPosition(newPosition));
    }

    @Test
    public void testIllegalFirstMoveThreeStepsForward() {
        Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 3);
        assertFalse(canMoveToPosition(newPosition));
    }
}
