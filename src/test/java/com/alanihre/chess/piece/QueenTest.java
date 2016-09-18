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
    public void testLegalMoveSouthEastDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveSouthWestDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 2);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveNorthEastDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveNorthWestDiagonal() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveForwards() {
        Point newPosition5 = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        assertTrue(queen.canMoveTo(newPosition5));
    }

    @Test
    public void testLegalMoveBackwards() {
        Point newPosition = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveRight() {
        Point newPosition = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        assertTrue(queen.canMoveTo(newPosition));
    }

    @Test
    public void testLegalMoveLeft() {
        Point newPosition = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        assertTrue(queen.canMoveTo(newPosition));
    }

    //TODO: Test illegal moves
}
