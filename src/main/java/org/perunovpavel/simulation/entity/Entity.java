package org.perunovpavel.simulation.entity;

public abstract class Entity {
    private final String emoji;

    protected Entity(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return emoji;
    }
}
