package org.perunovpavel.simulation.entity;


import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.service.FindCoordinatesService;

import java.util.Map;

public class Predator extends Creature {

    public Predator() {
        super("\uD83E\uDD81");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public boolean isTheRightCellForTheMove(WorldMap worldMap, Coordinate coordinate) {
        return worldMap.getMap().get(coordinate) == null || worldMap.getMap().get(coordinate) instanceof Herbivore;
    }

    @Override
    public void makeMove(WorldMap worldMap, Coordinate coordinate) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        Coordinate pathEntity = findCoordinateForTheMove(worldMap, coordinate);
        if (FindCoordinatesService.isIfThereHerbivore(map, pathEntity)) {
            map.remove(pathEntity);
        }
        System.out.println(this + " Сделал ход из " + coordinate + " в " + pathEntity);
        map.remove(coordinate);
        map.put(pathEntity, this);
        this.setMoved(true);
    }

}
