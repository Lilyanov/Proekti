package com.skrill.interns.airplaneHorns;

import java.util.List;

public class MachineA extends Machine {
    private final int REQUIRED_PART_A = 77;
    private final int REQUIRED_PART_B = 29;
    private final int PRODUCED_PART_D = 8;

    public MachineA(List<Station> stations, int productionTime, String name) throws IllegalArgumentException {
        super(stations, productionTime, name);

    }

    protected void load(Station station) {
        if (firstPart < REQUIRED_PART_A && station.getPartA() >= REQUIRED_PART_A) {
            station.removePartA(REQUIRED_PART_A);
            addFirstPart(REQUIRED_PART_A);
            System.out.println(REQUIRED_PART_A + " P_A are loaded in " + this.name + " from " + station.name);
        }

        if (this.secondPart < REQUIRED_PART_B && station.getPartB() >= REQUIRED_PART_B) {
            station.removePartB(REQUIRED_PART_B);
            addSecondPart(REQUIRED_PART_B);
            System.out.println(REQUIRED_PART_B + " P_B are loaded in " + this.name + " from " + station.name);
        }
    }

    protected void unload(Station station) {
        removeProducedPart(PRODUCED_PART_D);
        station.addPartD(PRODUCED_PART_D);
        System.out.println(this.name + " produced " + PRODUCED_PART_D + " P_D");
    }

    protected boolean checkIfReadyToProduce() {
        if ((firstPart >= REQUIRED_PART_A) && (secondPart >= REQUIRED_PART_B)) {
            return true;
        }
        return false;
    }

    protected void produce() {
        try {
            removeFirstPart(REQUIRED_PART_A);
            removeSecondPart(REQUIRED_PART_B);
            Thread.sleep(productionTime);
            addProducedPart(PRODUCED_PART_D);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
