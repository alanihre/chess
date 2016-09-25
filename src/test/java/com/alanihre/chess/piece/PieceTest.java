package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PieceTest extends PieceTestBase {

    private static PieceType PIECE_TYPE = PieceType.BISHOP;
    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    //@Rule
    //public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        piece = new StubPiece(PIECE_TYPE, PIECE_COLOR);
        super.setUp();
    }

    @Test
    public void testConstructorSetsType() {
        assertEquals(PIECE_TYPE, piece.getType());
    }

    @Test
    public void testConstructorSetsColor() {
        assertEquals(PIECE_COLOR, piece.getColor());
    }

    @Test
    public void testNumberOfMovesIsInitiallyZero() {
        assertEquals(0, piece.getNumberOfMoves());
    }

    @Test
    public void testIncreaseNumberOfMoves() {
        piece.increaseNumberOfMoves();
        assertEquals(1, piece.getNumberOfMoves());
    }

    @Test
    public void testMovingPiece() {
        Position newPosition = new Position(5, 6);
        Square newSquare = board.getSquareAtPosition(newPosition);
        piece.moveTo(newSquare);
        assertFalse(square.hasPiece());
        assertEquals(piece, newSquare.getPiece());
    }

    @Test
    public void testMovingPieceIncreasesNumberOfMoves() {
        Position newPosition = new Position(5, 6);
        Square newSquare = board.getSquareAtPosition(newPosition);
        piece.moveTo(newSquare);
        assertEquals(1, piece.getNumberOfMoves());
    }
/*
    @Test
    public void testCalculateForwardsMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculateBackwardsMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculateSidewaysRightMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculateSidewaysLeftMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculatePositiveDiagonalMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 1));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculateNegativeDiagonalMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 2));
        assertEquals(expectedMovementPath, movementPath);
    }

    @Test
    public void testCalculateNorthEastDiagonalMovementPath() {
        Position position = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2);
        List<Position> movementPath = piece.getMovementPathToPosition(position);
        List<Position> expectedMovementPath = new ArrayList<Position>();
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y - 1));
        expectedMovementPath.add(new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 2));
        assertEquals(expectedMovementPath, movementPath);
    }*/
}
