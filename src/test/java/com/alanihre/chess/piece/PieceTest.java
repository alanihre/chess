package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PieceTest {

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Piece piece;

    @Before
    public void setUp() {
        piece = new Piece(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR) {
            @Override
            public boolean canMoveTo(Point newPosition) {
                return false;
            }

            @Override
            public char getSymbol() {
                return 0;
            }

            @Override
            public String getPieceName() {
                return null;
            }
        };
    }

    @Test
    public void testConstructor() {
        assertEquals(piece.getColor(), PIECE_COLOR);
        assertEquals(piece.getPosition(), new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y));
    }

    @Test
    public void testIncreaseNumberOfMoves() {
        assertEquals(piece.getNumberOfMoves(), 0);
        piece.increaseNumberOfMoves();
        assertEquals(piece.getNumberOfMoves(), 1);
    }

    @Test
    public void testMovingPiece() {
        Point newPosition = new Point(5, 6);
        piece.moveTo(newPosition);
        assertEquals(piece.getPosition(), newPosition);
        assertEquals(piece.getNumberOfMoves(), 1);
    }

    @Test
    public void testGetMovementPathToPosition() {
        piece = spy(piece);

        //Single direction movement
        Point point = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 3);
        piece.getMovementPathToPosition(point);
        verify(piece).calculateSingleDirectionMovementPath(point);

        //Single direction movement
        Point point2 = new Point(PIECE_START_POSITION_X + 3, PIECE_START_POSITION_Y);
        piece.getMovementPathToPosition(point2);
        verify(piece).calculateSingleDirectionMovementPath(point2);

        //Diagonal movement
        Point point3 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        piece.getMovementPathToPosition(point3);
        verify(piece).calculateDiagonalMovementPath(point3);

        //Not supported movement
        Point point4 = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 1);
        thrown.expect(PieceMovementNotSupportedException.class);
        piece.getMovementPathToPosition(point4);
    }

    @Test
    public void testCalculateForwardsMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateBackwardsMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateSidewaysRightMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateSidewaysLeftMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculatePositiveDiagonalMovementMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateNegativeDiagonalMovementMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2));
        assertEquals(movementPath, expectedMovementPath);
    }
}
