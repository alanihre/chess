package com.alanihre.chess.piece;

import com.alanihre.chess.board.Board;
import com.alanihre.chess.board.Position;
import com.alanihre.chess.board.Square;
import com.alanihre.chess.board.StubBoard;

public class PieceTestBase {

    protected static Piece.PieceColor PIECE_COLOR = Piece.PieceColor.BLACK;
    protected static int PIECE_START_POSITION_X = 2;
    protected static int PIECE_START_POSITION_Y = 2;
    protected static int BOARD_SIZE_WIDTH = 8;
    protected static int BOARD_SIZE_HEIGHT = 9;
    protected Piece piece;
    protected Board board;
    protected Square square;

    public void setUp() {
        board = new StubBoard(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        square = new Square(board, new Position(PIECE_START_POSITION_X, PIECE_START_POSITION_Y));
        square.setPiece(piece);
    }

    protected boolean canMoveToPosition(Position newPosition) {
        Square newSquare = board.getSquareAtPosition(newPosition);
        return piece.canMoveTo(newSquare);
    }
}
