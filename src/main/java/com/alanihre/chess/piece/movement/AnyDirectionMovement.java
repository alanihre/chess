package com.alanihre.chess.piece.movement;

import com.alanihre.chess.board.Offset;
import com.alanihre.chess.board.Path;
import com.alanihre.chess.board.Position;

public class AnyDirectionMovement implements PieceMovement {

    private DiagonalMovement diagonalMovement;
    private FourDirectionMovement fourDirectionMovement;

    public AnyDirectionMovement() {
        diagonalMovement = new DiagonalMovement();
        fourDirectionMovement = new FourDirectionMovement();
    }

    public Path getPath(Position origin, Position destination) {
        Offset offset = origin.getOffsetTo(destination);

        if (offset.isDiagonal()) {
            return diagonalMovement.getPath(origin, destination);
        } else {
            return fourDirectionMovement.getPath(origin, destination);
        }
    }
}
