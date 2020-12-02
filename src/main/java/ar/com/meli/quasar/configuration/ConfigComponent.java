package ar.com.meli.quasar.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.meli.quasar.domain.ListSatellite;
import ar.com.meli.quasar.dto.Satellite;
import ar.com.meli.quasar.dto.Satellites;

@Configuration
public class ConfigComponent {
    @Autowired
    private ListSatellite listSatellite;
    @Bean
    public void LoadNameSatellistes() {
        Satellites satellitesDto = new Satellites();
        List<Satellite> satellites = new ArrayList<>();
        Satellite satellite = new Satellite("kenobi", 0, null);
        satellites.add(satellite);
        satellite = new Satellite("skywalker", 0, null);
        satellites.add(satellite);
        satellite = new Satellite("sato", 0, null);
        satellites.add(satellite);
        satellitesDto.setSatellites(satellites);
        listSatellite.setListSatellites(satellitesDto);
    }

}
