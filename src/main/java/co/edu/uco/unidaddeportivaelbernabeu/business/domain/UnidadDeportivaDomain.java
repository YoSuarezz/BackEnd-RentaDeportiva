package co.edu.uco.unidaddeportivaelbernabeu.business.domain;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class UnidadDeportivaDomain {

    private int id;
    private String nombre;
    private String ciudad;
    private String direccion;

    public UnidadDeportivaDomain() {
        setNombre(TextHelper.EMPTY);
        setCiudad(TextHelper.EMPTY);
        setDireccion(TextHelper.EMPTY);
    }

    public UnidadDeportivaDomain(final int id, final String nombre, final String ciudad, final String direccion) {
        setId(id);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);

    }
    public final int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public final String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = TextHelper.applyTrim(ciudad);
    }

    public static final UnidadDeportivaDomain crear(){
        return new UnidadDeportivaDomain();
    }

    public static final UnidadDeportivaDomain crear(final int id, final String nombre, final String ciudad, final String direccion) {
        return new UnidadDeportivaDomain(id, nombre, ciudad, direccion);
    }
}



