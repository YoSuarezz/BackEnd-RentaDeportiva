package co.edu.uco.unidaddeportivaelbernabeu.dto;

public class UsuarioIngresoDTO {

    private String usuario;
    private String contraseña;

    // Constructor vacío
    public UsuarioIngresoDTO() {
    }

    // Constructor con todos los parámetros
    public UsuarioIngresoDTO(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "UsuarioIngresoDTO{" +
                "usuario='" + usuario + '\'' +
                ", contraseña='[PROTECTED]'" +
                '}';
    }
}

