package com.skrill.interns.airplaneHorns;

public class Proba1 {

    /**
     * @param args
     */
    public static void proba(Proba p) {
        Proba p2 = new Proba();
        p2.a = 10;
        p = p2;
        System.out.println(p2.a);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Proba p1 = new Proba();
        p1.a = 1000;
        proba(p1);
        System.out.println(p1.a);
    }

}
