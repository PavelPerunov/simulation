package org.perunovpavel.simulation;

import org.perunovpavel.simulation.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final Map<Coordinate, Entity> map;
    public final int length; // длина матрицы
    public final int height;// высота матрицы

    public WorldMap(int height, int length) {
        this.height = height;
        this.length = length;
        map = new HashMap<>();
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Map<Coordinate, Entity> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "org.perunovpavel.simulation.WorldMap{" +
                "map=" + map +
                "}\n";
    }
}
