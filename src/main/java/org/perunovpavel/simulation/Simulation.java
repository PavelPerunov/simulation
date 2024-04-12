package org.perunovpavel.simulation;


import org.perunovpavel.simulation.actions.Action;
import org.perunovpavel.simulation.actions.initActions.*;
import org.perunovpavel.simulation.actions.turnActions.MoveCreatureAction;
import org.perunovpavel.simulation.service.PrintMenuService;
import org.perunovpavel.simulation.service.PrintWorldService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private final WorldMap worldMap;
    private final List<Action> initAction;
    private final List<Action> turnAction;
    private final Object lock = new Object();
    private boolean pause = true;
    private final Scanner scanner = new Scanner(System.in);
    public static int turnCounter = 0;


    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;

        initAction = new ArrayList<>(List.of(
                new GrassSpawnAction(),
                new HerbivoreSpawnAction(),
                new PredatorSpawnAction(),
                new RockSpawnAction(),
                new TreeSpawnAction())
        );

        turnAction = new ArrayList<>(List.of(new MoveCreatureAction()));

        for (Action action : initAction) {
            action.process(this.worldMap);
        }

    }

    public void nextTurn() {
        for (Action turn : turnAction) {
            turn.process(worldMap);
        }
        System.out.println();
        PrintWorldService.printWorld(worldMap);
    }


    public void startSimulation() {

        Thread thread = createThreadForSimulation();

        System.out.println("Вы решили начать симуляцию, ваша карта:\n");
        PrintWorldService.printWorld(worldMap);

        thread.start();

        while (true) {

            if (getTurnCounter() != 0) {
                System.out.println("Ход " + getTurnCounter());
            }

            printContextMenu();
        }

    }


    private void printContextMenu() {
        if (pause) {

            PrintMenuService.startSimulationMenu();

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> continueSimulation();
                case "2" -> nextTurn();
                case "0" -> System.exit(0);
                default -> System.out.println("Неверный ввод, введите корректное значение!!!");
            }

        } else {
            if (scanner.nextLine().equals("1")) {
                pauseSimulation();
            } else if (scanner.nextLine().equals("0")) {
                System.exit(0);
            } else {
                System.out.println("Некорректный ввод!!!\nВведите [1] чтобы закончить автоматическую симуляцию\nВведите [0] чтобы выйти из приложения");
            }
        }
    }

    private void pauseSimulation() {
        pause = true;
    }


    private Thread createThreadForSimulation() {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (pause) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                PrintMenuService.infinitySimulationMenu();
                nextTurn();

                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void continueSimulation() {
        pause = false;
        synchronized (lock) {
            lock.notify();
        }

    }

    public static int getTurnCounter() {
        return turnCounter;
    }
}
