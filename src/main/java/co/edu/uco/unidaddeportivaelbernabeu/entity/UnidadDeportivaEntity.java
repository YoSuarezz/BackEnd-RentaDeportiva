package co.edu.uco.unidaddeportivaelbernabeu.entity;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public final class UnidadDeportivaEntity {

    private int id;
    private String nombre;
    private String ciudad;
    private String direccion;

    private UnidadDeportivaEntity(final int id) {
        setId(id);
        setNombre(TextHelper.EMPTY);
        setCiudad(TextHelper.EMPTY);
        setDireccion(TextHelper.EMPTY);
    }

    private UnidadDeportivaEntity(int id, String nombre, String ciudad, String direccion) {
        setId(id);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
    }

    public static final UnidadDeportivaEntity build(final int id){
        return new UnidadDeportivaEntity(id);
    }

    public static final UnidadDeportivaEntity build(final int id, final String nombre, final String ciudad, final String direccion) {
        return new UnidadDeportivaEntity(id, nombre, ciudad, direccion);
    }

    protected static final UnidadDeportivaEntity build() {
        return new UnidadDeportivaEntity(NumericHelper.ZERO);
    }

    public final int getId() {
        return id;
    }

    private final void setId(int id) {
        this.id = id;
    }

    public final String getNombre() {
        return nombre;
    }

    private final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final String getCiudad() {
        return ciudad;
    }

    private final void setCiudad(String ciudad) {
        this.ciudad = TextHelper.applyTrim(ciudad);
    }

    public final String getDireccion() {
        return direccion;
    }

    private final void setDireccion(String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
    }
}

