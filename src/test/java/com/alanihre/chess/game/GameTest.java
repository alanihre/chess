package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.piece.Pawn;
import com.alanihre.chess.piece.Piece;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GameTest {

    private Game game;
    private Piece piece;

    private static int BOARD_SIZE_WIDTH = 8;
    private static int BOARD_SIZE_HEIGHT = 9;
    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;

    @Before
    public void setUp() {
        game = new Game(null) {
            @Override
            protected Board initializeBoard() {
                Board board = new Board(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT) {
                    @Override
                    public void setupBoard() {
                        piece = new Pawn(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
                        this.addPiece(piece);
                    }
                };
                board.setupBoard();
                return board;
            }

            @Override
            void prepareForNextMove() {

            }

            @Override
            boolean pieceCanCapturePiece(Piece capturingPiece, Piece targetPiece) {
                return false;
            }
        };
    }

    @Test
    public void testCapturePiece() {
        game.capturePiece(piece);
        assertNull(game.getBoard().getPieceAtPosition(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y)));
    }

    //TODO: Write more tests
}
