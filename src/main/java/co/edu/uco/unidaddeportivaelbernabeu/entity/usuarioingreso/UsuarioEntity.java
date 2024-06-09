package co.edu.uco.unidaddeportivaelbernabeu.entity.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class UsuarioEntity {

    private int id;
    private String usuario;
    private String contrasena;
    private boolean activo;

    public UsuarioEntity(final int id) {
        setId(id);
        setUsuario(TextHelper.EMPTY);
        setContrasena(TextHelper.EMPTY);
        setActivo(activo);
    }

    public UsuarioEntity(final int id, final String usuario, final String contrasena, final boolean activo) {
        setId(id);
        setUsuario(usuario);
        setContrasena(contrasena);
        setActivo(activo);
    }

    public static final UsuarioEntity build(final int id) {
        return new UsuarioEntity(id);
    }

    public static final UsuarioEntity build(){
        return new UsuarioEntity(NumericHelper.ZERO);
    }

    public static final UsuarioEntity build(final int id, final String usuario, final String contrasena, final boolean activo) {
        return new UsuarioEntity(id, usuario, contrasena, activo);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public final UsuarioEntity setUsuario(final String usuario) {
        this.usuario = TextHelper.applyTrim(usuario);
        return this;
    }

    public String getContrasena() {
        return contrasena;
    }

    public final UsuarioEntity setContrasena(String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
