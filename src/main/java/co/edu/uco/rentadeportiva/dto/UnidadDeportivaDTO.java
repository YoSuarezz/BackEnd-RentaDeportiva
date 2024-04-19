package co.edu.uco.rentadeportiva.dto;

import co.edu.uco.rentadeportiva.crosscutting.helpers.ObjectHelper;
import co.edu.uco.rentadeportiva.crosscutting.helpers.TextHelper;

public class UnidadDeportivaDTO {
    private int id;
    private String nombre;
    private int numeroDeDocumento;
    private String direccion;
    private int telefono;
    private CiudadDTO ciudad;
    private ValidacionDTO correo;
    private ResponsableDTO responsable;
    private TipoDeDocumentoDTO tipoDeDocumento;

    //public UnidadDeportivaDTO() {
    //}

    //public UnidadDeportivaDTO() {
    //}

    public final int getId() {
        return id;
    }

    public final UnidadDeportivaDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final UnidadDeportivaDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final int getNumeroDeDocumento() {
        return numeroDeDocumento;
    }

    public final UnidadDeportivaDTO setNumeroDeDocumento(final int numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento;
        return this;
    }

    public final String getDireccion() {
        return direccion;
    }

    public final UnidadDeportivaDTO setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }

    public final int getTelefono() {
        return telefono;
    }

    public final UnidadDeportivaDTO setTelefono(final int telefono) {
        this.telefono = telefono;
        return this;
    }

    public final CiudadDTO getCiudad() {
        return ciudad;
    }

    public final UnidadDeportivaDTO setCiudad(final CiudadDTO ciudad) {
        this.ciudad = ObjectHelper.getObjectHelper().getDefault(ciudad, CiudadDTO.build());
        return this;
    }

    public final ValidacionDTO getCorreo() {
        return correo;
    }

    public final UnidadDeportivaDTO setCorreo(final ValidacionDTO correo) {
        this.correo = ObjectHelper.getObjectHelper().getDefault(correo, ValidacionDTO.build());
        return this;
    }

    public final ResponsableDTO getResponsable() {
        return responsable;
    }

    public final UnidadDeportivaDTO setResponsable(final ResponsableDTO responsable) {
        this.responsable = ObjectHelper.getObjectHelper().getDefault(responsable, ResponsableDTO.build());
        return this;
    }

    public final TipoDeDocumentoDTO getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public final UnidadDeportivaDTO setTipoDeDocumento(final TipoDeDocumentoDTO tipoDeDocumento) {
        this.tipoDeDocumento = ObjectHelper.getObjectHelper().getDefault(tipoDeDocumento, TipoDeDocumentoDTO.build());
        return this;
    }
}
