package co.edu.uco.unidaddeportivaelbernabeu.api.response;

import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;

import java.util.ArrayList;
import java.util.List;

public class TipoEspacioDeportivoResponse extends Response<TipoEspacioDeportivoDTO> {

    public static final TipoEspacioDeportivoResponse build(final List<String> mensajes, final List<TipoEspacioDeportivoDTO> datos) {

        TipoEspacioDeportivoResponse instance = new TipoEspacioDeportivoResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final TipoEspacioDeportivoResponse build(final List<TipoEspacioDeportivoDTO> datos) {

        TipoEspacioDeportivoResponse instance = new TipoEspacioDeportivoResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final TipoEspacioDeportivoResponse build() {
        TipoEspacioDeportivoResponse instance = new TipoEspacioDeportivoResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }


}