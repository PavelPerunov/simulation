package org.perunovpavel.simulation.actions;

import org.perunovpavel.simulation.WorldMap;

//TODO мне кажется называть метод как класс это хуйня, но я не уверен, лучше переименуй метод
public abstract class Action {
    public abstract void process(WorldMap worldMap);
}
