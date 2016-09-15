package com.alanihre.chess.piece;

import com.alanihre.chess.Point;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PieceTest {

    private Piece piece;

    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static Point PIECE_START_POSITION = new Point(1, 2);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        piece = new Piece(PIECE_START_POSITION, PIECE_COLOR) {
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
        assertEquals(piece.getPosition(), PIECE_START_POSITION);
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

        //Moving from (1,2) to (1,5) which is a single direction movement
        Point point = new Point(1, 5);
        piece.getMovementPathToPosition(point);
        verify(piece).calculateSingleDirectionMovementPath(point);

        //Moving from (1,2) to (2,2) which is a single direction movement
        Point point2 = new Point(2, 2);
        piece.getMovementPathToPosition(point2);
        verify(piece).calculateSingleDirectionMovementPath(point2);

        //Moving from (1,2) to (2,3) which is a diagonal movement
        Point point3 = new Point(2, 3);
        piece.getMovementPathToPosition(point3);
        verify(piece).calculateDiagonalMovementPath(point3);

        //Moving from (1,2) to (2,3) which is a diagonal movement
        Point point4 = new Point(2, 4);
        thrown.expect(PieceMovementNotSupportedException.class);
        piece.getMovementPathToPosition(point4);
    }

    @Test
    public void testCalculateForwardsMovementPath() {
        Point point = new Point(1, 5);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(1, 3));
        expectedMovementPath.add(new Point(1, 4));
        expectedMovementPath.add(new Point(1, 5));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateBackwardsMovementPath() {
        Point point = new Point(1, 0);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(1, 1));
        expectedMovementPath.add(new Point(1, 0));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateSidewaysLeftMovementPath() {
        Point point = new Point(3, 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(2, 2));
        expectedMovementPath.add(new Point(3, 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateSidewaysRightMovementPath() {
        Point point = new Point(0, 2);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(0, 2));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculatePositiveDiagonalMovementMovementPath() {
        Point point = new Point(3, 4);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(2, 3));
        expectedMovementPath.add(new Point(3, 4));
        assertEquals(movementPath, expectedMovementPath);
    }

    @Test
    public void testCalculateNegativeDiagonalMovementMovementPath() {
        Point point = new Point(0, 1);
        List<Point> movementPath = piece.getMovementPathToPosition(point);
        List<Point> expectedMovementPath = new ArrayList<Point>();
        expectedMovementPath.add(new Point(0, 1));
        assertEquals(movementPath, expectedMovementPath);
    }
}
