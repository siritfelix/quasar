package ar.com.meli.quasar.domain;

import org.springframework.stereotype.Component;

import ar.com.meli.quasar.dto.Satellites;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ListSatellite {
    private Satellites ListSatellites;
    
}
