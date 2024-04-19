package co.edu.uco.rentadeportiva.dto;

import co.edu.uco.rentadeportiva.crosscutting.helpers.ObjectHelper;
import co.edu.uco.rentadeportiva.crosscutting.helpers.TextHelper;

public class UnidadDeportivaDTO {
    private int id;
    private String nombre;
    private int numeroDeDocumento;
    private String direccion;
    private String telefono;
    private CiudadDTO ciudad;
    private ValidacionDTO correo;
    private ResponsableDTO responsable;
    private TipoDeDocumentoDTO tipoDeDocumento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeDocumento() {
        return numeroDeDocumento;
    }

    public void setNumeroDeDocumento(int numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public ValidacionDTO getCorreo() {
        return correo;
    }

    public void setCorreo(ValidacionDTO correo) {
        this.correo = correo;
    }

    public ResponsableDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableDTO responsable) {
        this.responsable = responsable;
    }

    public TipoDeDocumentoDTO getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(TipoDeDocumentoDTO tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }
}
