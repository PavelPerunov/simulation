package org.perunovpavel.simulation.entity;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.service.FindCoordinatesService;

import java.util.List;

public abstract class Creature extends Entity {
    private boolean isMoved;

    public Creature(String emoji) {
        super(emoji);
    }

    public abstract void makeMove(WorldMap worldMap, Coordinate coordinate);

    public abstract boolean isTheRightCellForTheMove(WorldMap worldMap,Coordinate coordinate);

    public boolean isMoved() {
        return isMoved;
    }
    protected Coordinate findCoordinateForTheMove(WorldMap worldMap, Coordinate coordinate) {
        List<Coordinate> path = FindCoordinatesService.findPath(worldMap, coordinate);
        Coordinate targetCell;
        if (path != null)
            targetCell = path.get(1);
        else {
            targetCell = FindCoordinatesService.findCoordinates(worldMap, coordinate);
        }
        return targetCell;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }
}
