package org.perunovpavel.simulation.service;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Herbivore;

import java.util.Map;


public class CounterHerbivoreService {
    public static int getCountHerbivore(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        int countOfHerbivore = 0;
        for (Entity entity : map.values()) {
            if (entity instanceof Herbivore) {
                countOfHerbivore++;
            }
        }
        return countOfHerbivore;

    }
}
