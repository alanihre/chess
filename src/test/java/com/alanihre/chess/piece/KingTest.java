package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest extends PieceTestBase {

  @Before
  public void setUp() {
    piece = new King(PIECE_COLOR);
    super.setUp();
  }

  @Test
  public void testLegalMoveForwards() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testIllegalMoveForwards() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y + 2);
    assertFalse(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveRight() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testIllegalMoveRight() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y);
    assertFalse(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveBackwards() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testIllegalMoveBackwards() {
    Position newPosition = new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y - 2);
    assertFalse(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveLeft() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testIllegalMoveLeft() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y);
    assertFalse(canMoveToPosition(newPosition));
  }
}
