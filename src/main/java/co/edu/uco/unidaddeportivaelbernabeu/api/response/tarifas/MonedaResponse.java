package co.edu.uco.unidaddeportivaelbernabeu.api.response.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.Response;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.MonedaDTO;

import java.util.ArrayList;
import java.util.List;

public class MonedaResponse extends Response<MonedaDTO> {

    public static final MonedaResponse build(final List<String> mensajes, final List<MonedaDTO> datos) {

        MonedaResponse instance = new MonedaResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final MonedaResponse build(final List<MonedaDTO> datos) {

        MonedaResponse instance = new MonedaResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final MonedaResponse build() {

        MonedaResponse instance = new MonedaResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }
}
