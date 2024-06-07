package co.edu.uco.unidaddeportivaelbernabeu.dto;

public class UsuarioIngresoDTO {

    private String usuario;
    private String contrasena;

    // Constructor vacío
    public UsuarioIngresoDTO() {
    }

    // Constructor con todos los parámetros
    public UsuarioIngresoDTO(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioIngresoDTO{" +
                "usuario='" + usuario + '\'' +
                ", contrasena='[PROTECTED]'" +
                '}';
    }
}
