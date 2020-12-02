package ar.com.meli.quasar.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Satellite {
    private String name;
    private float distance;
    private String[] message;

    @Override
    public boolean equals(Object o) {

        Satellite satellite = (Satellite) o;
        return satellite.getName().equalsIgnoreCase(this.name);
    }

}
