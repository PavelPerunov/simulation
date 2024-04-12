package org.perunovpavel.simulation.actions.initActions;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Predator;
import org.perunovpavel.simulation.service.FindRandomEmptyCoordinateService;

import java.util.Map;

public class PredatorSpawnAction extends Action {
    private int count = 4;

    @Override
    public void process(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        while (count != 0) {
            Coordinate coordinate = FindRandomEmptyCoordinateService.findRandomCoordinates(worldMap);
            if (FindRandomEmptyCoordinateService.isEmpty(map, coordinate)) {
                map.put(coordinate, new Predator());
                count--;
            }
        }
    }
}
