package co.edu.uco.unidaddeportivaelbernabeu.service;

import co.edu.uco.unidaddeportivaelbernabeu.dto.UsuarioIngresoDTO;

public interface UsuarioIngresoService {
    boolean validarUsuario(UsuarioIngresoDTO usuarioIngreso);
}
