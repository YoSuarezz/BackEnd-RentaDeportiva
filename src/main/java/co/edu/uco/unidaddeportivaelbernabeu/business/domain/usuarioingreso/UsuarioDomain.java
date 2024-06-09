package co.edu.uco.unidaddeportivaelbernabeu.business.domain.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class UsuarioDomain {

    private int id;
    private String usuario;
    private String contrasena;
    private boolean activo;

    private UsuarioDomain(final int id,final String usuario,final String contrasena,final boolean activo) {
        setId(id);
        setUsuario(usuario);
        setContrasena(contrasena);
        setActivo(activo);
    }

    private UsuarioDomain(){
        setUsuario(TextHelper.EMPTY);
        setContrasena(TextHelper.EMPTY);
        setActivo(activo);
    }

    public static final UsuarioDomain crear(final int id,final String usuario,final String contrasena,final boolean activo) {
        return new UsuarioDomain(id,usuario,contrasena,activo);
    }

    public static final UsuarioDomain crear(){
        return new UsuarioDomain();
    }

    public int getId() {
        return id;
    }

    private final void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    private final void setUsuario(String usuario) {
        this.usuario = TextHelper.applyTrim(usuario);
    }

    public String getContrasena() {
        return contrasena;
    }

    private final void setContrasena(String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
