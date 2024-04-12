package org.perunovpavel.simulation.service;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.entity.Creature;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Grass;
import org.perunovpavel.simulation.entity.Herbivore;

import java.util.*;

public class FindCoordinatesService {

    public static Coordinate findCoordinates(WorldMap worldMap, Coordinate coordinates) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        int x, y;
        x = coordinates.getX();
        y = coordinates.getY();
        boolean isHerbivore = map.get(coordinates) instanceof Herbivore;
        List<Coordinate> coordinatesList = new ArrayList<>();
        if (x + 1 <= worldMap.getHeight() && isIfThereSuchFacility(map, x + 1, y, isHerbivore)) {
            coordinatesList.add(new Coordinate(x + 1, y));
        }
        if (x - 1 > 0 && isIfThereSuchFacility(map, x - 1, y, isHerbivore)) {
            coordinatesList.add(new Coordinate(x - 1, y));
        }
        if (y - 1 > 0 && isIfThereSuchFacility(map, x, y - 1, isHerbivore)) {
            coordinatesList.add(new Coordinate(x, y - 1));
        }
        if (y + 1 <= worldMap.getLength() && isIfThereSuchFacility(map, x, y + 1, isHerbivore)) {
            coordinatesList.add(new Coordinate(x, y + 1));
        }
        if (coordinatesList.isEmpty()) {
            return coordinates;
        } else {
            return coordinatesList.get((int) (Math.random() * coordinatesList.size()));
        }

    }

    public static List<Coordinate> findPath(WorldMap worldMap, Coordinate coordinate) {
        Map<Coordinate, Entity> map = worldMap.getMap();

        Creature creature = (Creature) map.get(coordinate);

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<List<Coordinate>> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();  // Множество для отслеживания посещенных клеток

        List<Coordinate> initialPath = new ArrayList<>();
        initialPath.add(coordinate);

        queue.add(initialPath);
        visited.add(coordinate);

        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate lastPoint = currentPath.get(currentPath.size() - 1);
            Entity currentEntity = map.get(lastPoint);

            if (creature instanceof Herbivore) {
                if (currentEntity instanceof Grass) {
                    return currentPath;
                }
            } else {
                if (currentEntity instanceof Herbivore) {
                    return currentPath;
                }
            }

            for (int j = 0; j < 4; j++) {
                int nx = lastPoint.getX() + dx[j];
                int ny = lastPoint.getY() + dy[j];

                Coordinate nextCell = new Coordinate(nx, ny);

                if (isThisCorrectCoordinate(worldMap, nextCell, creature) && !visited.contains(nextCell)) {
                    List<Coordinate> newPath = new ArrayList<>(currentPath);
                    newPath.add(nextCell);
                    queue.add(newPath);
                    visited.add(nextCell);
                }
            }
        }

        // Возвращаем null, если не найден путь
        return null;
    }



    private static boolean isThisCorrectCoordinate(WorldMap worldMap, Coordinate coordinate, Creature creature) {
        int nx = coordinate.getX();
        int ny = coordinate.getY();
        return nx >= 1 && nx <= worldMap.height && ny >= 1 && ny <= worldMap.length && creature.isTheRightCellForTheMove(worldMap,coordinate );
    }

    public static boolean isIfThereSuchFacility(Map<Coordinate, Entity> map, int x, int y, boolean isHerbivore) {
        if (isHerbivore) {
            return isIfThereGrass(map, new Coordinate(x, y)) || map.get(new Coordinate(x, y)) == null;
        } else {
            return isIfThereHerbivore(map, new Coordinate(x, y)) || map.get(new Coordinate(x, y)) == null;
        }
    }

    public static boolean isIfThereGrass(Map<Coordinate, Entity> map, Coordinate coordinate) {
        return map.get(coordinate) instanceof Grass;
    }

    public static boolean isIfThereHerbivore(Map<Coordinate, Entity> map, Coordinate coordinate) {
        return map.get(coordinate) instanceof Herbivore;
    }

}
