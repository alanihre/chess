package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public interface PieceMovement {
    Path getPath(Position origin, Position target);
}
