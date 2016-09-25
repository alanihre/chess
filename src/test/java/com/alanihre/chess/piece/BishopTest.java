package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BishopTest extends PieceTestBase {

    @Before
    public void setUp() {
        piece = new Bishop(PIECE_COLOR);
        super.setUp();
    }

    @Test
    public void testLegalMoveSouthEastDiagonal() {
        Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testLegalMoveSouthWestDiagonal() {
        Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 2);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testLegalMoveNorthEastDiagonal() {
        Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testLegalMoveNorthWestDiagonal() {
        Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        assertTrue(canMoveToPosition(newPosition));
    }

    @Test
    public void testIllegalMoveNonDiagonal() {
        Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertFalse(canMoveToPosition(newPosition));
    }
}
