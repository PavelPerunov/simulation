package org.perunovpavel.simulation;

import org.perunovpavel.simulation.service.PrintMenuService;

import java.util.Scanner;

public class Launcher {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(5, 5);

        PrintMenuService.startMenu();
        while (true) {
            switch (scanner.nextLine()) {
                case "1" -> play(worldMap);
                case "0" -> System.exit(0);
                default -> System.out.println("Некорректный ввод! Введите 1 или 0");
            }
        }
    }

    public static void play(WorldMap worldMap) {
        Simulation simulation = new Simulation(worldMap);
        simulation.startSimulation();
    }
}
