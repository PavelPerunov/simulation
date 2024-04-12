package org.perunovpavel.simulation.service;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.entity.Entity;

import java.util.Map;

public class PrintWorldService {
    public static void printWorld(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                if ((map.get(new Coordinate(x, y)) == null)) {
                    System.out.print("\uD83C\uDFFD" + "  ");
                } else {
                    System.out.print(map.get(new Coordinate(x, y)) + "  ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }
}
