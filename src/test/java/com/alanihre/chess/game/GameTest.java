package com.alanihre.chess.game;

import com.alanihre.chess.Point;
import com.alanihre.chess.board.Board;
import com.alanihre.chess.piece.Knight;
import com.alanihre.chess.piece.Piece;
import com.alanihre.chess.piece.PieceType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class GameTest {

    private static int BOARD_SIZE_WIDTH = 8;
    private static int BOARD_SIZE_HEIGHT = 9;
    private static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    private static int PIECE_START_POSITION_X = 2;
    private static int PIECE_START_POSITION_Y = 2;
    private Game game;
    private Piece piece;
    private GameDelegate gameDelegate;

    @Before
    public void setUp() {
        gameDelegate = new GameDelegate() {
            public void gameReady(Game game) {

            }

            public void requestMove(Piece.PieceColor color) {

            }

            public void pieceCaptured(Piece piece, String coordinate) {

            }

            public void pieceMoved(Piece piece, String coordinate) {

            }

            public PieceType requestNewPieceOfType(PieceType[] availableTypes, String message) {
                return null;
            }
        };

        game = new Game(gameDelegate) {
            @Override
            protected Board initializeBoard() {
                Board board = new Board(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT) {
                    @Override
                    public void setupBoard() {
                        piece = new Knight(new Point(PIECE_START_POSITION_X, PIECE_START_POSITION_Y), PIECE_COLOR);
                        this.putPiece(piece);
                    }
                };
                board.setupBoard();
                return board;
            }

            @Override
            void prepareForNextMove() {

            }

            @Override
            void pieceMoved(Piece piece, Point oldPosition) {

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
