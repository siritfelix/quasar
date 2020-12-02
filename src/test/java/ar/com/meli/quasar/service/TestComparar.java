package ar.com.meli.quasar.service;

import org.junit.jupiter.api.Test;

public class TestComparar {

    LocationImpl locationImpl =new LocationImpl();

    @Test
    void comparar() {
        String[] m1 = { "", "C", "D", "E", "F" };
        String[] m2 = { "", "D", "E", "", "", "" };
        String[] m3 = { "", "", "A", "B", "C" };
        System.out.println(locationImpl.GetMessage(m1, m2, m3));
    }


}
