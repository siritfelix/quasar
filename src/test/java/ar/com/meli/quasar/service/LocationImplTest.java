package ar.com.meli.quasar.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.meli.quasar.domain.ListSatellite;
import ar.com.meli.quasar.dto.PositionResponse;
import ar.com.meli.quasar.dto.Satellite;
import ar.com.meli.quasar.dto.Satellites;

@SpringBootTest
public class LocationImplTest {

    @Autowired
    private ILocation iLocation;
    @Autowired
    private ListSatellite listSatellite;

    @BeforeEach
    public void config() {
        Satellite satellite;
        List<Satellite> satellites = new ArrayList<>();
        String[] message1 = { "x", "", "z" };
        satellite = new Satellite("kenobi", (float) 538.516, message1);
        satellites.add(satellite);
        String[] message2 = { "", "y", "z" };
        satellite = new Satellite("skywalker", (float) 141.42, message2);
        satellites.add(satellite);
        String[] message3 = { "x", "", "z" };
        satellite = new Satellite("sato", (float) 509.9, message3);
        satellites.add(satellite);
        Satellites satellitesDto = new Satellites();
        satellitesDto.setSatellites(satellites);
        listSatellite.setListSatellites(satellitesDto);

    }

    @Test
    public void testposition() {

        PositionResponse position = iLocation.TopSecret();
        Assertions.assertNotEquals(position.getPosition(), null);

    } 

}
