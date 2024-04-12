package org.perunovpavel.simulation.actions.turnActions;

import org.perunovpavel.simulation.Coordinate;
import org.perunovpavel.simulation.Simulation;
import org.perunovpavel.simulation.WorldMap;
import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.actions.turnActions.respawnAction.GrassRespawnAction;
import org.perunovpavel.simulation.actions.turnActions.respawnAction.HerbivoreRespawnAction;
import org.perunovpavel.simulation.entity.Creature;
import org.perunovpavel.simulation.entity.Entity;

import java.util.Map;

public class MoveCreatureAction extends Action {

    @Override
    public void process(WorldMap worldMap) {
        Action grassRespawn = new GrassRespawnAction();
        Action herbivoreRespawn = new HerbivoreRespawnAction();
        grassRespawn.process(worldMap);
        herbivoreRespawn.process(worldMap);
        Map<Coordinate, Entity> map = worldMap.getMap();
        for (int x = 1; x <= worldMap.height; x++) {
            for (int y = 1; y <= worldMap.length; y++) {
                Entity entity = map.get(new Coordinate(x, y));
                if (entity instanceof Creature) {
                    if (!((Creature) entity).isMoved()) {
                        ((Creature) entity).makeMove(worldMap, new Coordinate(x, y));
                    }
                }
            }
        }
        clearIsMoved(worldMap);
        Simulation.turnCounter++;
    }


    public static void clearIsMoved(WorldMap worldMap) {
        Map<Coordinate, Entity> map = worldMap.getMap();
        for (Entity entity : map.values()) {
            if (entity instanceof Creature) {
                ((Creature) entity).setMoved(false);
            }
        }
    }
}
