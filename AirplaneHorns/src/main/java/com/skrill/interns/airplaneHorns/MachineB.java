package com.skrill.interns.airplaneHorns;

import java.util.List;

public class MachineB extends Machine {
    private final int REQUIRED_PART_C = 22;
    private final int REQUIRED_PART_D = 7;
    private final int PRODUCED_PART_E = 5;

    public MachineB(List<Station> stations, int productionTime, String name) throws IllegalArgumentException {
        super(stations, productionTime, name);

    }

    protected void load(Station station) {
        if ((firstPart < REQUIRED_PART_C) && (station.getPartC() >= REQUIRED_PART_C)) {
            station.removePartC(REQUIRED_PART_C);
            addFirstPart(REQUIRED_PART_C);
            System.out.println(REQUIRED_PART_C + " P_C are loaded in " + this.name + " from " + station.name);
        }

        if ((this.secondPart < REQUIRED_PART_D) && (station.getPartD() >= REQUIRED_PART_D)) {
            station.removePartD(REQUIRED_PART_D);
            addSecondPart(REQUIRED_PART_D);
            System.out.println(REQUIRED_PART_D + " P_D are loaded in " + this.name + " from " + station.name);
        }
    }

    protected void unload(Station station) {
        removeProducedPart(PRODUCED_PART_E);
        station.addPartE(PRODUCED_PART_E);
        System.out.println(this.name + " produced " + PRODUCED_PART_E + " P_E");
    }

    protected boolean checkIfReadyToProduce() {
        if ((firstPart >= REQUIRED_PART_C) && (secondPart >= REQUIRED_PART_D)) {
            return true;
        } else {
            return false;
        }
    }

    protected void produce() {
        try {
            removeFirstPart(REQUIRED_PART_C);
            removeSecondPart(REQUIRED_PART_D);
            Thread.sleep(productionTime);
            addProducedPart(PRODUCED_PART_E);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
