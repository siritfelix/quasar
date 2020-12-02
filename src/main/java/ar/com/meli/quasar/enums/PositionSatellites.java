package ar.com.meli.quasar.enums;

public enum PositionSatellites {
    
     S1("Kenobi", (float) -500.0, (float) -200.0), S2("Skywalker", (float) 100.0,
     (float) -100.0), S3("Sato", (float) 500.0, (float) 100.0);
     

    /*S1("Kenobi", (float) -6.0, (float) -2.0), S2("Skywalker", (float) -1.0, (float) 7.0),
    S3("Sato", (float) 4.0, (float) 3.0);*/

    private final String name;
    private final Float x;
    private final Float y;
    private final Float Error = (float) 5.0;

    private PositionSatellites(String name, Float x, Float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String satellite() {
        return this.name;
    }

    public Float X() {
        return this.x;
    }

    public Float Y() {
        return this.y;
    }

    public Float error() {
        return this.Error;
    }

}
