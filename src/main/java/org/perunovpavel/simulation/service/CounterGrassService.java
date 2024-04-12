package org.perunovpavel.simulation.service;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Grass;

import java.util.Map;


public class CounterGrassService {
    public static int getCountGrass(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        int countGrass = 0;
        for (Entity entity : map.values()) {
            if (entity instanceof Grass) {
                countGrass++;
            }
        }
        return countGrass;
    }
}
