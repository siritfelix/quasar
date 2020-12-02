package ar.com.meli.quasar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.meli.quasar.domain.ListSatellite;
import ar.com.meli.quasar.dto.Position;
import ar.com.meli.quasar.dto.PositionResponse;
import ar.com.meli.quasar.dto.Satellite;
import ar.com.meli.quasar.enums.PositionSatellites;
import ar.com.meli.quasar.util.Error;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LocationImpl implements ILocation {

    @Autowired
    private ListSatellite listSatellite;

    @Override
    public PositionResponse TopSecret() {
        float r1 = 0, r2 = 0, r3 = 0;
        String[] m1 = null, m2 = null, m3 = null;
        for (Satellite s : listSatellite.getListSatellites().getSatellites()) {
            if (s.getName().equalsIgnoreCase(PositionSatellites.S1.satellite())) {
                r1 = s.getDistance();
                m1 = s.getMessage();
            }
            if (s.getName().equalsIgnoreCase(PositionSatellites.S2.satellite())) {
                r2 = s.getDistance();
                m2 = s.getMessage();
            }
            if (s.getName().equalsIgnoreCase(PositionSatellites.S3.satellite())) {
                r3 = s.getDistance();
                m3 = s.getMessage();
            }
        }
        PositionResponse positionResponse = new PositionResponse();
        positionResponse.setPosition(GetLocation(r1, r2, r3));
        positionResponse.setMenssage(GetMessage(m1, m2, m3));
        return positionResponse;
    }

    @Override
    public Position GetLocation(Float r1, Float r2, Float r3) {

        Position position;
        Float x, y;
        Float S2X = PositionSatellites.S2.X() - PositionSatellites.S1.X();
        Float S2Y = PositionSatellites.S2.Y() - PositionSatellites.S1.Y();
        Float S3X = PositionSatellites.S3.X() - PositionSatellites.S1.X();
        Float S3Y = PositionSatellites.S3.Y() - PositionSatellites.S1.Y();

        Float d = S2X * S3Y - S3X * S2Y;
        if (d == 0) {
            log.info("Error en posicion de satelites");
            return null;
        }

        x = (S3Y * (float) (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(S2X, 2) + Math.pow(S2Y, 2))
                - S2Y * (float) (Math.pow(r1, 2) - Math.pow(r3, 2) + Math.pow(S3X, 2) + Math.pow(S3Y, 2)))
                / (2 * (S2X * S3Y - S3X * S2Y));
        y = (S2X * (float) (Math.pow(r1, 2) - Math.pow(r3, 2) + Math.pow(S3X, 2) + Math.pow(S3Y, 2))
                - S3X * (float) (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(S2X, 2) + Math.pow(S2Y, 2)))
                / (2 * (S2X * S3Y - S3X * S2Y));

        x = x + PositionSatellites.S1.X();
        y = y + PositionSatellites.S1.Y();
        if (Error.validar(r1, r2, r3, x, y).equals(true)) {
            position = new Position(x, y);
            log.info("Ubicacion calculada X = " + x + " Y = " + y);
        } else {
            log.info("Ubicacion calculada pero no valida X = " + x + " Y = " + y);
            return null;
        }
        return position;
    }

    @Override
    public String GetMessage(String[] m1, String[] m2, String[] m3) {
        Integer lengM, fase1 = 0, fase2 = 0, fase3 = 0;
        if (m1 != null && m2 != null && m3 != null) {
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m2.length; j++) {
                    if (!m1[i].isEmpty() && m1[i].equals(m2[j])) {
                        fase2 = j - i;
                        i = m1.length;
                        j = m2.length;
                    }
                }

            }
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m3.length; j++) {
                    if (!m1[i].isEmpty() && m1[i].equals(m3[j])) {
                        fase3 = j - i;
                        i = m1.length;
                        j = m3.length;
                    }
                }

            }
            Integer faseM = fase1 > fase2 ? fase1 : fase2;
            faseM = faseM > fase3 ? faseM : fase3;
            lengM = m1.length > m2.length ? m1.length : m2.length;
            lengM = lengM > m3.length ? lengM : m3.length;
            String Message[] = new String[lengM + faseM];

            for (int a = 0; a < m1.length; a++) {
                if (!m1[a].isEmpty())
                    Message[a + faseM - fase1] = m1[a];
            }
            for (int a = 0; a < m2.length; a++) {
                if (!m2[a].isEmpty())
                    Message[a + faseM - fase2] = m2[a];
            }
            for (int a = 0; a < m3.length; a++) {
                if (!m3[a].isEmpty())
                    Message[a + faseM - fase3] = m3[a];
            }
            List<String> ListMessage = Arrays.asList(Message);
            List<String> ListMessageCls = ListMessage.stream().filter(p -> p != null).collect(Collectors.toList());
            StringBuilder MessageOut = new StringBuilder();
            System.out.println(ListMessageCls.toString());
            for (int i = 0; i < ListMessageCls.size(); i++) {
                MessageOut.append(ListMessageCls.get(i));
                if (i < ListMessageCls.size() - 1)
                    MessageOut.append(" ");

            }
            return MessageOut.toString();
        } else
            return null;
    }

}
