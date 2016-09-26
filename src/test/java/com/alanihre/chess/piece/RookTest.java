package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RookTest extends PieceTestBase {

  @Before
  public void setUp() {
    piece = new Rook(PIECE_COLOR);
    super.setUp();
  }

  @Test
  public void testLegalMoveForward() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveBackward() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testIllegalMoveInTwoDirections() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 2);
    assertFalse(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveRight() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveLeft() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
    assertTrue(canMoveToPosition(newPosition));
  }
}
