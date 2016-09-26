package com.alanihre.chess;

import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Position;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionTest {

  private static int FILE = 1;
  private static int RANK = 2;

  private Position position;

  @Before
  public void setUp() {
    position = new Position(FILE, RANK);
  }

  @Test
  public void testConstructor() {
    assertEquals(FILE, position.getFile());
    assertEquals(RANK, position.getRank());
  }

  @Test
  public void testEqualPositions() {
    Position position2 = new Position(FILE, RANK);
    assertTrue(position.equals(position2));
  }

  @Test
  public void testNotEqualPositions() {
    Position position3 = new Position(1, 3);
    assertFalse(position.equals(position3));
  }

  @Test
  public void testToString() {
    String pointAsString = position.toString();
    assertEquals("(" + FILE + "," + RANK + ")", pointAsString);
  }

  @Test
  public void testGetOffsetTo() {
    Position offsetPosition = new Position(FILE + 2, RANK - 1);
    Offset expectedOffset = new Offset(2, -1);
    Offset actualOffset = position.getOffsetTo(offsetPosition);
    assertEquals(expectedOffset.getX(), actualOffset.getX());
    assertEquals(expectedOffset.getY(), actualOffset.getY());
  }
}
