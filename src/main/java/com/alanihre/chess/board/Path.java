package com.alanihre.chess.board;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Position> nodes = new ArrayList<Position>();

    public void addNode(Position position) {
        nodes.add(position);
    }

    public Position getDestination() {
        return nodes.get(nodes.size() - 1);
    }

    public List<Position> getNodes() {
        return nodes;
    }

    public int getNodeCount() {
        return nodes.size();
    }
}
