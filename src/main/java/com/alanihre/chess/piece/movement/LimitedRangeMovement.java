package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class LimitedRangeMovement implements PieceMovement {
    private PieceMovement movement;
    private int maxRange;

    public LimitedRangeMovement(PieceMovement movement, int maxRange) {
        this.movement = movement;
        this.maxRange = maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public Path getPath(Position origin, Position destination) {
        Path path = movement.getPath(origin, destination);

        if (path != null && path.getNodeCount() > maxRange - 1) {
            return null;
        }

        return path;
    }
}