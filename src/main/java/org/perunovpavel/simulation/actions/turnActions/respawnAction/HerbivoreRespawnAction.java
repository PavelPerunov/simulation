package org.perunovpavel.simulation.actions.turnActions.respawnAction;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.actions.initActions.HerbivoreSpawnAction;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Herbivore;
import org.perunovpavel.simulation.service.CounterHerbivoreService;
import org.perunovpavel.simulation.service.FindRandomEmptyCoordinateService;

import java.util.Map;

public class HerbivoreRespawnAction extends Action {
    @Override
    public void process(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        HerbivoreSpawnAction herbivoreSpawnAction = new HerbivoreSpawnAction();
        int defaultCountOfHerbivore = herbivoreSpawnAction.getCount();
        int currentHerbivore = CounterHerbivoreService.getCountHerbivore(worldMap);
        if (currentHerbivore <= 1) {
            while (currentHerbivore != defaultCountOfHerbivore) {
                Coordinate coordinate = FindRandomEmptyCoordinateService.findRandomCoordinates(worldMap);
                if (FindRandomEmptyCoordinateService.isEmpty(map, coordinate)) {
                    map.put(coordinate, new Herbivore());
                    currentHerbivore++;
                }
            }
        }
    }
}
