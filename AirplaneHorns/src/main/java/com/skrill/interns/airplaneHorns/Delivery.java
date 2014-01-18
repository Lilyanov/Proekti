package com.skrill.interns.airplaneHorns;

import java.util.List;

public class Delivery implements Runnable {
    private final int UNLOADING = 2000;
    private List<Station> stations;
    private Distributors distributor;

    public Delivery(Distributors distributor, List<Station> stations) throws IllegalArgumentException {
        if (distributor == null || stations == null) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        this.distributor = distributor;
        this.stations = stations;
    }

    protected void chooseDelivery(Station currentStation) {
        switch (distributor) {
        case A: {
            currentStation.addPartA(distributor.quantity());
            System.out.println(currentStation.name + " received 200 P_A");
            break;
        }
        case B: {
            currentStation.addPartB(distributor.quantity());
            System.out.println(currentStation.name + " received 50 P_B");
            break;
        }
        case C: {
            currentStation.addPartC(distributor.quantity());
            System.out.println(currentStation.name + " received 20 P_C");
            break;
        }
        }
    }

    protected boolean findFreeStation() {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).locker.tryLock()) {
                try {
                    chooseDelivery(stations.get(i));
                    Thread.sleep(UNLOADING); // wait for unloading
                } catch (InterruptedException e) {
                    System.out.println("The distributor stop to deliver");
                } finally {
                    stations.get(i).locker.unlock();
                }
                return true;
            }
        }
        return false;
    }

    protected void waitingForDelivery() {
        try {
            Thread.sleep(distributor.frequency());
        } catch (InterruptedException e) {
            System.out.println("The distributor stop to deliver");
        }
    }

    @Override
    public void run() {
        while (true) {
            if (findFreeStation()) {
                waitingForDelivery();
            }
        }
    }
}




