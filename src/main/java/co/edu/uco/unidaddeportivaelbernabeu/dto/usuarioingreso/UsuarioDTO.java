package co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class UsuarioDTO {

    private int id;
    private String usuario;
    private String contrasena;
    private boolean activo;

    public UsuarioDTO() {
        setUsuario(TextHelper.EMPTY);
        setContrasena(TextHelper.EMPTY);
        setActivo(activo);
    }

    public UsuarioDTO(final int id,final String usuario,final String contrasena,final boolean activo) {
        setId(id);
        setUsuario(usuario);
        setContrasena(contrasena);
        setActivo(activo);
    }

    public static final UsuarioDTO build(){
        return new UsuarioDTO();
    }

    public final int getId() {
        return id;
    }

    public final UsuarioDTO setId(int id) {
        this.id = id;
        return this;
    }

    public final String getUsuario() {
        return usuario;
    }

    public final UsuarioDTO setUsuario(final String usuario) {
        this.usuario = TextHelper.applyTrim(usuario);
        return this;
    }

    public final String getContrasena() {
        return contrasena;
    }

    public final UsuarioDTO setContrasena(String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
        return this;
    }

    public final boolean isActivo() {
        return activo;
    }

    public UsuarioDTO setActivo(boolean activo) {
        this.activo = activo;
        return this;
    }
}
