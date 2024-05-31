package co.edu.uco.unidaddeportivaelbernabeu.entity.espaciosdeportivos;

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

    public UnidadDeportivaEntity(final int id, final String nombre, final String ciudad, final String direccion) {
        setId(id);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
    }

    public static final UnidadDeportivaEntity build(final int id) {
        return new UnidadDeportivaEntity(id);
    }

    public static final UnidadDeportivaEntity build(final int id, final String nombre, final String ciudad, final String direccion) {
        return new UnidadDeportivaEntity(id, nombre, ciudad, direccion);
    }

    public static final UnidadDeportivaEntity build() {
        return new UnidadDeportivaEntity(NumericHelper.ZERO);
    }

    public final int getId() {
        return id;
    }

    public final UnidadDeportivaEntity setId(int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final UnidadDeportivaEntity setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final String getCiudad() {
        return ciudad;
    }

    public final UnidadDeportivaEntity setCiudad(String ciudad) {
        this.ciudad = TextHelper.applyTrim(ciudad);
        return this;
    }

    public final String getDireccion() {
        return direccion;
    }

    public final UnidadDeportivaEntity setDireccion(String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }
}
