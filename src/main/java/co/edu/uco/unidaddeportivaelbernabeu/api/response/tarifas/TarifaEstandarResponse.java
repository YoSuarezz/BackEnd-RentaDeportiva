package co.edu.uco.unidaddeportivaelbernabeu.api.response.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.Response;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;

import java.util.ArrayList;
import java.util.List;

public class TarifaEstandarResponse extends Response<TarifaEstandarDTO> {

    public static final TarifaEstandarResponse build(final List<String> mensajes, final List<TarifaEstandarDTO> datos){

        TarifaEstandarResponse instance = new TarifaEstandarResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final TarifaEstandarResponse build(final List<TarifaEstandarDTO> datos) {

        TarifaEstandarResponse instance = new TarifaEstandarResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final TarifaEstandarResponse build() {
        TarifaEstandarResponse instance = new TarifaEstandarResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }


}
