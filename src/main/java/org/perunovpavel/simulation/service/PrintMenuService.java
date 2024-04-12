package org.perunovpavel.simulation.service;

public class PrintMenuService {

    public static void startMenu() {
        System.out.println("Добро пожаловать в симуляцию\n" +
                "1 - начать симуляцию\n" +
                "0 - выйти из симуляции");
    }


    public static void startSimulationMenu() {
        System.out.println("1 - начать бессконечную симуляцию\n" +
                "2 - сделать ход\n" +
                "0 - выйти из приложения");
    }

    public static void infinitySimulationMenu() {
        System.out.println("1 - остановить симуляцию\n" +
                "0 - выйти из симуляции\n");
    }
}
