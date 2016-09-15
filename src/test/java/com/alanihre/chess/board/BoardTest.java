package com.alanihre.chess.board;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.alanihre.chess.Point;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;
import java.util.Arrays;

public class BoardTest {

    private static int BOARD_SIZE_WIDTH = 8;
    private static int BOARD_SIZE_HEIGHT = 9;

    private Board board;

    @Before
    public void setUp() {
        board = new Board(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT) {
            public void setupBoard() {

            }
        };
    }

    @Test
    public void testConstructor() {
        assertEquals(board.getWidth(), BOARD_SIZE_WIDTH);
        assertEquals(board.getHeight(), BOARD_SIZE_HEIGHT);
    }

    @Test
    public void testCreateHorizontalLabels() {
        char[] horizontalLabels = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        assertTrue(Arrays.equals(board.getHorizontalLabels(), horizontalLabels));
    }

    @Test
    public void testCreateVerticalLabels() {
        char[] verticalLabels = {'9', '8', '7', '6', '5', '4', '3', '2', '1'};
        assertTrue(Arrays.equals(board.getVerticalLabels(), verticalLabels));
    }

    @Test
    public void testPositionWithinBoardBounds() {
        Point pointOutsideBoardBounds = new Point(8, 9);
        assertFalse(board.positionWithinBoardBounds(pointOutsideBoardBounds));

        Point pointInsideBoardBounds = new Point(1, 1);
        assertTrue(board.positionWithinBoardBounds(pointInsideBoardBounds));
    }

    @Test
    public void testAddingPiece() {
        Point position = new Point(0, 0);
        Piece piece = new Pawn(position, Piece.PieceColor.BLACK);

        board.addPiece(piece);
        assertSame(board.getPieceAtPosition(position), piece);
    }

    @Test
    public void testRemovingPiece() {
        Point position = new Point(0, 0);
        Piece piece = new Pawn(position, Piece.PieceColor.BLACK);

        board.addPiece(piece);
        board.removePiece(piece);
        assertNull(board.getPieceAtPosition(position));
    }

    @Test
    public void testBoardPointToLabeledPoint() {
        Point point = new Point(1, 2);
        String expectedLabeledPoint = "b7";
        assertEquals(board.boardPointToLabeledPoint(point), expectedLabeledPoint);
    }

    @Test
    public void testLabeledPointToBoardPoint() {
        String labeledPoint = "b7";
        Point expectedPoint = new Point(1, 2);
        assertEquals(board.labeledPointToBoardPoint(labeledPoint), expectedPoint);
    }
}
