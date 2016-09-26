package com.alanihre.chess.piece;

import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class KnightTest extends PieceTestBase {

  @Before
  public void setUp() {
    piece = new Knight(PIECE_COLOR);
    super.setUp();
  }

  @Test
  public void testLegalMoveRightForward() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y + 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveRightBackward() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 2, PIECE_START_POSITION_Y - 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveLeftForward() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y + 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveLeftBackward() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 2, PIECE_START_POSITION_Y - 1);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveForwardRight() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y + 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveForwardLeft() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y + 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveBackwardRight() {
    Position newPosition = new Position(PIECE_START_POSITION_X + 1, PIECE_START_POSITION_Y - 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  @Test
  public void testLegalMoveBackwardLeft() {
    Position newPosition = new Position(PIECE_START_POSITION_X - 1, PIECE_START_POSITION_Y - 2);
    assertTrue(canMoveToPosition(newPosition));
  }

  //TODO: Test illegal moves

  @Test
  public void testCanLeap() {
    assertTrue(piece.canLeap());
  }
}
