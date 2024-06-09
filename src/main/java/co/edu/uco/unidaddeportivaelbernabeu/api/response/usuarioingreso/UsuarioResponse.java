package co.edu.uco.unidaddeportivaelbernabeu.api.response.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.Response;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioResponse extends Response<UsuarioDTO> {

    public static final UsuarioResponse build(final List<String> mensajes, final List<UsuarioDTO> datos) {
        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final UsuarioResponse build(final List<UsuarioDTO> datos) {

        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final UsuarioResponse build() {

        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }
}

