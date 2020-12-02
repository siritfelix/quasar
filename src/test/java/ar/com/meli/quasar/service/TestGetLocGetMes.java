package ar.com.meli.quasar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.com.meli.quasar.dto.Position;

public class TestGetLocGetMes {

    LocationImpl locationImpl = new LocationImpl();

    @Test
    void GetMessage() {
        String[] m1 = { "", "C", "D", "E", "F" };
        String[] m2 = { "", "D", "E", "", "", "" };
        String[] m3 = { "", "", "A", "B", "C" };
        String result = locationImpl.GetMessage(m1, m2, m3);
        Assertions.assertEquals("A B C D E F", result);
    }

    @Test
    void GetLocation() {
        Float r1 = (float) 538.516;
        Float r2 = (float) 141.42;
        Float r3 = (float) 509.9;
        Position position = locationImpl.GetLocation(r1, r2, r3);
        Assertions.assertNotEquals(position, null);
    }

    @Test
    void GetLocationNull() {
        Float r1 = (float) -1;
        Float r2 = (float) 0;
        Float r3 = (float) 5;
        Position position = locationImpl.GetLocation(r1, r2, r3);
        Assertions.assertNull(position);
    }
}