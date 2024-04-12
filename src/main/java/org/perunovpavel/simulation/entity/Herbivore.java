package org.perunovpavel.simulation.entity;


import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.service.FindCoordinatesService;

import java.util.Map;


public class Herbivore extends Creature {

    @Override
    public void makeMove(WorldMap worldMap, Coordinate coordinate) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        Coordinate pathEntity = findCoordinateForTheMove(worldMap, coordinate);
        if (FindCoordinatesService.isIfThereGrass(map, pathEntity)) {
            eatGrass(map, pathEntity);
        }
        map.remove(coordinate);
        System.out.println(this + " Сделал ход из " + coordinate + " в " + pathEntity);
        map.put(pathEntity, this);
        this.setMoved(true);
    }

    public void eatGrass(Map<Coordinate, Entity> map, Coordinate coordinate) {
        map.remove(coordinate);
        System.out.println("Я съел траву. Eё координаты были " + coordinate);
    }

    @Override
    public boolean isTheRightCellForTheMove(WorldMap worldMap, Coordinate coordinate) {
        return worldMap.getMap().get(coordinate) == null || worldMap.getMap().get(coordinate) instanceof Grass;
    }

    public Herbivore() {
        super("\uD83D\uDC30");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
