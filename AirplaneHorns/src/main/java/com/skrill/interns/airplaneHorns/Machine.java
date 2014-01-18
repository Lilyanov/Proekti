package com.skrill.interns.airplaneHorns;

import java.util.List;

public abstract class Machine implements Runnable {
    int productionTime;
    int firstPart;
    int secondPart;
    int producedPart;
    List<Station> stations;
    String name;

    public Machine(List<Station> stations, int productionTime, String name) throws IllegalArgumentException {
        if (stations == null) {
            throw new IllegalArgumentException();
        }
        this.stations = stations;
        this.productionTime = productionTime;
        this.name = name;
        this.firstPart = 0;
        this.secondPart = 0;
        this.producedPart = 0;
    }

    public void addFirstPart(int firstPart) {
        if (firstPart >= 0) {
            this.firstPart += firstPart;
        }
    }

    public void addSecondPart(int secondPart) {
        if (secondPart >= 0) {
            this.secondPart += secondPart;
        }
    }

    public void addProducedPart(int producedPart) {
        if (producedPart >= 0) {
            this.producedPart += producedPart;
        }
    }

    public void removeFirstPart(int firstPart) {
        if (firstPart >= 0) {
            this.firstPart -= firstPart;
        }
    }

    public void removeSecondPart(int secondPart) {
        if (secondPart >= 0) {
            this.secondPart -= secondPart;
        }
    }

    public void removeProducedPart(int producedPart) {
        if (producedPart >= 0) {
            this.producedPart -= producedPart;
        }
    }

    abstract void produce();

    abstract boolean checkIfReadyToProduce();

    abstract void load(Station station);

    abstract void unload(Station station);

    public Station findAndLockFreeStation() {
        Station currentStation;

        while (true) {
            for (int i = 0; i < stations.size(); i++) {
                currentStation = stations.get(i);
               // if (currentStation.locker.tryLock()) {
                synchronized(currentStation) {
                    load(currentStation);
                }
                 //   currentStation.locker.unlock();
                 //   return currentStation;
              //  }
            }
        }
    }

    public void run() {
        while (true) {

            //Station station = findAndLockFreeStation();
        	Station currentStation = null;
            for (int i = 0; i < stations.size(); i++) {
            	currentStation = stations.get(i);
            	load(currentStation);
            }
            if (checkIfReadyToProduce()) {
            	produce();
            	unload(currentStation);
            }
        
       }
    }
}
