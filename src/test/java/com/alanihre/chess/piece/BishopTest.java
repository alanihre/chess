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
    public void testLegalMoveSouthEastDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertTrue(bishop.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveSouthWestDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 2);
        assertTrue(bishop.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveNorthEastDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        assertTrue(bishop.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveNorthWestDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        assertTrue(bishop.canMoveTo(newPosition));
    }

    @Test
    public void testIllegalMoveNonDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertFalse(bishop.canMoveTo(newPosition));
    }
}
