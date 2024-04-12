package org.perunovpavel.simulation.actions.initActions;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Tree;
import org.perunovpavel.simulation.service.FindRandomEmptyCoordinateService;

import java.util.Map;

public class TreeSpawnAction extends Action {
    private int count = 2;

    @Override
    public void process(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        while (count != 0) {
            Coordinate coordinate = FindRandomEmptyCoordinateService.findRandomCoordinates(worldMap);
            if (FindRandomEmptyCoordinateService.isEmpty(map, coordinate)) {
                map.put(coordinate, new Tree());
                count--;
            }
        }
    }
}
