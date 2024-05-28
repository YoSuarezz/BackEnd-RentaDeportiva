package co.edu.uco.unidaddeportivaelbernabeu.api.response.deporte;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.Response;
import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;

import java.util.ArrayList;
import java.util.List;

public class UnidadDeportivaResponse extends Response<UnidadDeportivaDTO> {
    public static final UnidadDeportivaResponse build(final List<String> mensajes, final List<UnidadDeportivaDTO> datos) {

        UnidadDeportivaResponse instance = new UnidadDeportivaResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final UnidadDeportivaResponse build(final List<UnidadDeportivaDTO> datos) {

        UnidadDeportivaResponse instance = new UnidadDeportivaResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final UnidadDeportivaResponse build() {

        UnidadDeportivaResponse instance = new UnidadDeportivaResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }
}

