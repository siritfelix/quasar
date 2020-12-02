package ar.com.meli.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.meli.quasar.domain.ListSatellite;
import ar.com.meli.quasar.dto.PositionResponse;
import ar.com.meli.quasar.dto.Satellite;
import ar.com.meli.quasar.dto.Satellites;
import ar.com.meli.quasar.service.ILocation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Trilateracion: ", description = "Determinacion de la posicion de un objeto mediante las distancias desde tres puntos ")
public class QuasarController {
    private ILocation iLocation;

    @Autowired
    public void setiLocation(ILocation iLocation) {
        this.iLocation = iLocation;
    }

    @Autowired
    ListSatellite listSatellite;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data obtained successfully"),
            @ApiResponse(responseCode = "204", description = "No Data found"),
            @ApiResponse(responseCode = "500", description = "Internal Error") })
    @PostMapping(value = "/topsecret/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> topsecret(@RequestBody Satellites satellites) {
        listSatellite.setListSatellites(satellites);
        PositionResponse positionResponse = new PositionResponse();
        positionResponse = iLocation.TopSecret();
        if (positionResponse.getPosition() == null)
            return new ResponseEntity<>(positionResponse, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(positionResponse, HttpStatus.OK);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data obtained successfully"),
            @ApiResponse(responseCode = "204", description = "No Data found"),
            @ApiResponse(responseCode = "500", description = "Internal Error") })
    @PostMapping(value = "/topsecret_split/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> topsecretSplit(@RequestParam(name = "satellite_name") String satelliteName,
            @RequestBody Satellite satellite) {
        satellite.setName(satelliteName);
        Integer index = 0;
        try {
            index = listSatellite.getListSatellites().getSatellites().indexOf(satellite);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (index >= 0) {
            listSatellite.getListSatellites().getSatellites().get(index).setDistance(satellite.getDistance());
            listSatellite.getListSatellites().getSatellites().get(index).setMessage(satellite.getMessage());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PositionResponse positionResponse = new PositionResponse();
        positionResponse = iLocation.TopSecret();
        if (positionResponse.getPosition() == null)
            return new ResponseEntity<>(positionResponse, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(positionResponse, HttpStatus.OK);
    }

}
