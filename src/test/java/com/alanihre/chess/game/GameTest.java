package com.alanihre.chess.game;

import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.piece.Knight;
import com.alanihre.chess.piece.Piece;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class GameTest {

  private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
  private static int PIECE_START_POSITION_X = 2;
  private static int PIECE_START_POSITION_Y = 2;
  private Game game;
  private Piece piece;
  private GameDelegate gameDelegate;

  @Before
  public void setUp() {
    gameDelegate = new StubGameDelegate();
    game = new StubGame(gameDelegate);

    piece = new Knight(PIECE_COLOR);
    game.getBoard().putPiece(piece, new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y));
  }

  @Test
  public void testCapturePiece() {
    game.capturePiece(piece);
    Square square = game.getBoard().getSquareAtPosition(new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y));
    assertNull(square.getPiece());
  }
}
