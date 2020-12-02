package ar.com.meli.quasar.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ar.com.meli.quasar.domain.ListSatellite;
import ar.com.meli.quasar.dto.Position;
import ar.com.meli.quasar.dto.PositionResponse;
import ar.com.meli.quasar.dto.Satellite;
import ar.com.meli.quasar.dto.Satellites;
import ar.com.meli.quasar.service.ILocation;

public class QuasarControllerTest {

    @InjectMocks
    private QuasarController quasarController;

    @Mock
    private ILocation iLocation;

    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private ListSatellite listSatellite;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(quasarController).build();
    }

    @Test
    void PosttopSecretOk() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(post("/topsecret/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellitesDto))).andExpect(status().isOk());

    }

    @Test
    void PosttopSecretBadRequest() throws JsonProcessingException, Exception {

        when(iLocation.TopSecret()).thenReturn(null);
        this.mvc.perform(post("/topsecret/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(null))).andExpect(status().isBadRequest());

    }

    @Test
    void PosttopSecretNoFound() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        positionResponse.setPosition(null);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(post("/topsecret/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellitesDto))).andExpect(status().isNotFound());

    }

    @Test
    void PosttopSecretNoFound2() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(null);
        PositionResponse positionResponse = new PositionResponse();
        positionResponse.setPosition(null);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(post("/topsecret/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellitesDto))).andExpect(status().isNotFound());

    }

    @Test
    void topsecretSplitPostOk() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(post("/topsecret_split/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellite)).param("satellite_name", "sato"))
                .andExpect(status().isOk());

    }

    @Test
    void topsecretSplitPostNoFound() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(post("/topsecret_split/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellite)).param("satellite_name", "name"))
                .andExpect(status().isNotFound());

    }

    @Test
    void topsecretSplitPostNoFound2() throws JsonProcessingException, Exception {
        Satellite satellite;
        String[] message1 = { "x", "", "z" };
        satellite = new Satellite("kenobi", (float) 538.516, message1);

        when(listSatellite.getListSatellites()).thenReturn(null);
        this.mvc.perform(post("/topsecret_split/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(satellite)).param("satellite_name", "name"))
                .andExpect(status().isNotFound());

    }

    @Test
    void topsecretSplitGetOk() throws JsonProcessingException, Exception {
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
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(get("/topsecret_split/")).andExpect(status().isOk());

    }
    @Test
    void topsecretSplitGetNoFound() throws JsonProcessingException, Exception {
        Satellite satellite;
        List<Satellite> satellites = new ArrayList<>();
        String[] message1 = { "x", "", "z" };
        satellite = new Satellite("kenobi", (float) 538.516, message1);
        satellites.add(satellite);
        String[] message2 = { "", "y", "z" };
        satellite = new Satellite("skywalker", (float) 141.42, message2);
        satellites.add(satellite);
        Satellites satellitesDto = new Satellites();
        satellitesDto.setSatellites(satellites);
        listSatellite.setListSatellites(satellitesDto);
        when(listSatellite.getListSatellites()).thenReturn(satellitesDto);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(get("/topsecret_split/")).andExpect(status().isNotFound());

    }
    @Test
    void topsecretSplitGetNoFound2() throws JsonProcessingException, Exception {

        when(listSatellite.getListSatellites()).thenReturn(null);
        PositionResponse positionResponse = new PositionResponse();
        Position position = new Position((float) 1.0, (float) 1.0);
        positionResponse.setPosition(position);
        positionResponse.setMenssage("menssage");
        when(iLocation.TopSecret()).thenReturn(positionResponse);
        this.mvc.perform(get("/topsecret_split/")).andExpect(status().isNotFound());

    }

}
