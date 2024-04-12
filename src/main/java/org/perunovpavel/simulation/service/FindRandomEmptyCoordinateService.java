package org.perunovpavel.simulation.service;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.entity.Entity;

import java.util.Map;
import java.util.Random;

public class FindRandomEmptyCoordinateService {

    public static Coordinate findRandomCoordinates(WorldMap worldMap) {
        int x = new Random().nextInt(worldMap.height) + 1;
        int y = new Random().nextInt(worldMap.length) + 1;
        return new Coordinate(x, y);

    }

    public static boolean isEmpty(Map<Coordinate, Entity> map, Coordinate coordinate) {
        return map.get(coordinate) == null;
    }

}
