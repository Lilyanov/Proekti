package com.skrill.interns.airplaneHorns;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Station {
    public String name;
    protected int partA;
    protected int partB;
    protected int partC;
    protected int partD;
    protected int partE;
    Lock locker = new ReentrantLock();

    public Station(String name) {
        partA = partB = partC = partD = partE = 0;
        this.name = name;
    }

    public int getPartA() {
        return partA;
    }

    public int getPartB() {
        return partB;
    }

    public int getPartC() {
        return partC;
    }

    public int getPartD() {
        return partD;
    }

    public int getPartE() {
        return partE;
    }

    public void addPartA(int quantity) {
        partA += quantity;
    }

    public void addPartB(int quantity) {
        partB += quantity;
    }

    public void addPartC(int quantity) {
        partC += quantity;
    }

    public void addPartD(int quantity) {
        partD += quantity;
    }

    public void addPartE(int quantity) {
        partE += quantity;
    }

    public synchronized void removePartA(int quantity) {
        partA -= quantity;
    }

    public synchronized void removePartB(int quantity) {
        partB -= quantity;
    }

    public synchronized void removePartC(int quantity) {
        partC -= quantity;
    }

    public synchronized void removePartD(int quantity) {
        partD -= quantity;
    }

    public void removePartE(int quantity) {
        partE -= quantity;
    }
}
