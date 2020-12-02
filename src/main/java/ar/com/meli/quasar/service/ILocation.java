package ar.com.meli.quasar.service;

import ar.com.meli.quasar.dto.Position;
import ar.com.meli.quasar.dto.PositionResponse;
import ar.com.meli.quasar.dto.Satellites;

public interface ILocation {

    public PositionResponse TopSecret();

    public Position GetLocation(Float r1, Float r2, Float r3);

    public String GetMessage(String[] m1, String[] m2, String[] m3);
}
