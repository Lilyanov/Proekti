package com.skrill.interns.airplaneHorns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirplaneHorns {
    private static final int MACHINE_A_THREADS = 2;
    private static final int MACHINE_B_THREADS = 2;
    private static final int DELIVERY_THREADS = 4;
    private static final int STATION_NUMBER = 2;
    private static List<Station> stations = new ArrayList<Station>();

    public static void main(String[] args) {
        for (int i = 0; i < STATION_NUMBER; i++) {
            stations.add(new Station("Station " + i));
        }
        ExecutorService deliveryExecutor = Executors.newFixedThreadPool(DELIVERY_THREADS);
        deliveryExecutor.submit(new Delivery(Distributors.A, stations));
        deliveryExecutor.submit(new Delivery(Distributors.B, stations));
        deliveryExecutor.submit(new Delivery(Distributors.C, stations));
        deliveryExecutor.submit(new Sender(stations));

        ExecutorService machineAExecutor = Executors.newFixedThreadPool(MACHINE_A_THREADS);
        for (int i = 0; i < MACHINE_A_THREADS; i++) {
            machineAExecutor.execute(new MachineA(stations, 7000, "M_A " + i));
        }
        ExecutorService machineBExecutor = Executors.newFixedThreadPool(MACHINE_B_THREADS);
        for (int i = 0; i < MACHINE_B_THREADS; i++) {
            machineBExecutor.execute(new MachineB(stations, 11000, "M_B " + i));
        }

        deliveryExecutor.shutdown();
        machineAExecutor.shutdown();
        machineBExecutor.shutdown();
    }
}
