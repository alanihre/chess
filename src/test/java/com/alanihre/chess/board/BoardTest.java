package com.alanihre.chess.board;

import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BoardTest {

  private static int BOARD_SIZE_WIDTH = 8;
  private static int BOARD_SIZE_HEIGHT = 9;

  private Board board;

  @Before
  public void setUp() {
    board = new StubBoard(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
  }

  @Test
  public void testConstructor() {
    assertEquals(BOARD_SIZE_WIDTH, board.getWidth());
    assertEquals(BOARD_SIZE_HEIGHT, board.getHeight());
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
    Position positionOutsideBoardBounds = new Position(BOARD_SIZE_WIDTH + 1, BOARD_SIZE_HEIGHT + 1);
    assertFalse(board.positionWithinBoardBounds(positionOutsideBoardBounds));

    Position positionInsideBoardBounds = new Position(BOARD_SIZE_WIDTH - 1, BOARD_SIZE_HEIGHT - 1);
    assertTrue(board.positionWithinBoardBounds(positionInsideBoardBounds));
  }

  @Test
  public void testPutPiece() {
    Position position = new Position(0, 0);
    Piece piece = new Pawn(Piece.PieceColor.BLACK);

    board.putPiece(piece, position);
    Piece actualPieceAtPosition = board.getSquareAtPosition(position).getPiece();
    assertSame(piece, actualPieceAtPosition);
  }

  @Test
  public void testBoardPointToLabeledPoint() {
    Position position = new Position(1, 2);
    String expectedLabeledPoint = "b7";
    String actualLabeledPoint = board.boardPointToLabeledPoint(position);
    assertEquals(expectedLabeledPoint, actualLabeledPoint);
  }

  @Test
  public void testLabeledPointToBoardPoint() {
    String labeledPoint = "b7";
    Position expectedPosition = new Position(1, 2);
    Position actualPosition = board.labeledPointToBoardPoint(labeledPoint);
    assertEquals(expectedPosition, actualPosition);
  }
}
