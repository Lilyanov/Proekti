package com.skrill.interns.airplaneHorns;

import java.util.List;

public class Sender implements Runnable {
    List<Station> stations;

    public Sender(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public void run() {
        Station currentStation;
        int amountPartE;
        while (true) {
            for (int i = 0; i < stations.size(); i++) {
                currentStation = stations.get(i);
                if (currentStation.getPartE() > 0 && currentStation.locker.tryLock()) {
                    amountPartE = currentStation.getPartE();
                    currentStation.removePartE(amountPartE);
                    currentStation.locker.unlock();
                    System.out.println(currentStation.name + " sends " + amountPartE + " P_E");
                }
            }
        }
    }

}
