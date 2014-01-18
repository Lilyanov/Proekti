package com.skrill.interns.airplaneHorns;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeliveryTest {

    List<Station> stations = new ArrayList<Station>();

    @BeforeMethod
    public void setup() {
        stations.add(spy(new Station("Station 1")));
    }

    @AfterMethod
    public void after() {
        stations.clear();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_create_Deliver_instance_with_null_arguments_throws_IllegalArgumentException() {
        // GIVEN
        new Delivery(null, null);
        // WHEN
        // THEN
        // throws IllegalArgumentException
    }

    @Test
    public void when_deliverA_make_delivery_then_partsA_in_the_station_increased() {
        // GIVEN
        Delivery deliverA = new Delivery(Distributors.A, stations);
        // WHEN
        deliverA.chooseDelivery(stations.get(0));
        // THEN
        verify(stations.get(0)).addPartA(200);
        assertEquals(stations.get(0).getPartA(), 200);
    }

    @Test
    public void when_deliverB_make_delivery_then_partsB_in_the_station_increased() {
        // GIVEN
        Delivery deliverB = new Delivery(Distributors.B, stations);
        // WHEN
        deliverB.chooseDelivery(stations.get(0));
        // THEN
        verify(stations.get(0)).addPartB(50);
        assertEquals(stations.get(0).getPartB(), 50);
    }

    @Test
    public void when_deliverC_make_delivery_then_partsC_in_the_station_increased() {
        // GIVEN
        Delivery deliverC = new Delivery(Distributors.C, stations);
        // WHEN
        deliverC.chooseDelivery(stations.get(0));
        // THEN
        verify(stations.get(0)).addPartC(20);
        assertEquals(stations.get(0).getPartC(), 20);
    }
}
