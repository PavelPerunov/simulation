package org.perunovpavel.simulation.actions.turnActions.respawnAction;


import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.actions.initActions.GrassSpawnAction;
import org.perunovpavel.simulation.entity.Entity;
import org.perunovpavel.simulation.entity.Grass;
import org.perunovpavel.simulation.service.CounterGrassService;
import org.perunovpavel.simulation.service.FindRandomEmptyCoordinateService;

import java.util.Map;

public class GrassRespawnAction extends Action {
    @Override
    public void process(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        GrassSpawnAction grassSpawnAction = new GrassSpawnAction();
        int defaultCountOfGrass = grassSpawnAction.getCount();
        int currentGrass = CounterGrassService.getCountGrass(worldMap);
        while (currentGrass != defaultCountOfGrass) {
            Coordinate coordinate = FindRandomEmptyCoordinateService.findRandomCoordinates(worldMap);
            if (FindRandomEmptyCoordinateService.isEmpty(map, coordinate)) {
                map.put(coordinate, new Grass());
                currentGrass++;
            }
        }
    }

}
