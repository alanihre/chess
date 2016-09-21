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

    private static PieceType PIECE_TYPE = PieceType.PAWN;
    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Piece piece;

    @Before
    public void setUp() {
        piece = new Piece(PieceType.PAWN, new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR) {
            @Override
            public boolean canMoveTo(Point newPosition) {
                return false;
            }

            @Override
            public String getPieceName() {
                return null;
            }

            @Override
            public char getSymbol() {
                return ' ';
            }
        };
    }

    @Test
    public void testConstructorSetsType() {
        assertEquals(piece.getType(), PIECE_TYPE);
    }

    @Test
    public void testConstructorSetsColor() {
        assertEquals(piece.getColor(), PIECE_COLOR);
    }

    @Test
    public void testConstructorSetsPosition() {
        assertEquals(piece.getPosition(), new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y));
    }

    @Test
    public void testNumberOfMovesIsInitiallyZero() {
        assertEquals(piece.getNumberOfMoves(), 0);
    }

    @Test
    public void testIncreaseNumberOfMoves() {
        piece.increaseNumberOfMoves();
        assertEquals(piece.getNumberOfMoves(), 1);
    }

    @Test
    public void testMovingPiece() {
        Point newPosition = new Point(5, 6);
        piece.moveTo(newPosition);
        assertEquals(piece.getPosition(), newPosition);
    }

    @Test
    public void testMovingPieceIncreasesNumberOfMoves() {
        Point newPosition = new Point(5, 6);
        piece.moveTo(newPosition);
        assertEquals(piece.getNumberOfMoves(), 1);
    }

    @Test
    public void testGetSupportedForwardMovement() {
        piece = spy(piece);

        Point point = new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 3);
        piece.getMovementPathToPosition(point);
        verify(piece).calculateSingleDirectionMovementPath(point);
    }

    @Test
    public void testGetSupportedSidewaysMovement() {
        piece = spy(piece);

        Point point = new Point(PIECE_START_POSITION_X + 3, PIECE_START_POSITION_Y);
        piece.getMovementPathToPosition(point);
        verify(piece).calculateSingleDirectionMovementPath(point);
    }

    @Test
    public void testGetSupportedDiagonalMovementPath() {
        piece = spy(piece);

        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        piece.getMovementPathToPosition(point);
        verify(piece).calculateDiagonalMovementPath(point);
    }

    @Test
    public void testGetUnsupportedMovementPathThrowsException() {
        piece = spy(piece);

        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 1);
        thrown.expect(PieceMovementNotSupportedException.class);
        piece.getMovementPathToPosition(point);
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
    public void testCalculatePositiveDiagonalMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateNegativeDiagonalMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateNorthEastDiagonalMovementPath() {
        Point point = new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Point(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2));
        assertEquals(movementPath, expectedMovementPath);
    }
}
