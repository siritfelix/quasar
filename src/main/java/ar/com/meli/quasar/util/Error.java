package ar.com.meli.quasar.util;

import ar.com.meli.quasar.enums.PositionSatellites;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Error {

    static public Boolean validar(Float r1, Float r2, Float r3, Float x, Float y) {
        Float error;
        Float r1c = (float) Math
                .sqrt(Math.pow(x - PositionSatellites.S1.X(), 2) + Math.pow(y - PositionSatellites.S1.Y(), 2));
        error = (float) 100.0 * (r1 - r1c) / r1;
        if (error > PositionSatellites.S1.error() || error < -PositionSatellites.S1.error()) {
            log.error("Error Radio 1: " + error + " % " + " superior al margen establecido de : "
                    + PositionSatellites.S1.error() + "%");
            return false;
        }
        log.info("Error Radio 1: " + error + " % ");

        Float r2c = (float) Math
                .sqrt(Math.pow(x - PositionSatellites.S2.X(), 2) + Math.pow(y - PositionSatellites.S2.Y(), 2));
        error = (float) 100.0 * (r2 - r2c) / r2;
        if (error > PositionSatellites.S2.error() || error < -PositionSatellites.S2.error()) {
            log.warn("Error Radio 2: " + error + " % " + " superior al margen establecido de : "
                    + PositionSatellites.S2.error() + "%");
            return false;
        }
        log.info("Error Radio 2: " + error + " % ");

        Float r3c = (float) Math
                .sqrt(Math.pow(x - PositionSatellites.S3.X(), 2) + Math.pow(y - PositionSatellites.S3.Y(), 2));
        error = (float) 100.0 * (r3 - r3c) / r3;
        if (error > PositionSatellites.S3.error() || error < -PositionSatellites.S3.error()) {
            log.warn("Error % Radio 3: " + +error + " % " + " superior al margen establecido de : "
                    + PositionSatellites.S3.error() + "%");
            return false;
        }
        log.info("Error Radio 3: " + error + " % ");
        return true;
    }
}
