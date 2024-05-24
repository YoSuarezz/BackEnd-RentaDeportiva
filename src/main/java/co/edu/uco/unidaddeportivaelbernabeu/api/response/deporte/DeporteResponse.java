package co.edu.uco.unidaddeportivaelbernabeu.api.response.deporte;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.Response;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;

import java.util.ArrayList;
import java.util.List;

public class DeporteResponse extends Response<DeporteDTO> {

    public static final DeporteResponse build(final List<String> mensajes, final List<DeporteDTO> datos) {

        DeporteResponse instance = new DeporteResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final DeporteResponse build(final List<DeporteDTO> datos) {

        DeporteResponse instance = new DeporteResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final DeporteResponse build() {

        DeporteResponse instance = new DeporteResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }
}
