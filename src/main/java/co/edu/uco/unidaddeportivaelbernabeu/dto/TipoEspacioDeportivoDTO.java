package co.edu.uco.unidaddeportivaelbernabeu.dto;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;


public final class TipoEspacioDeportivoDTO {
    private int id;
    private UnidadDeportivaDTO unidadDeportiva;
    private DeporteDTO deporte;
    private String espacio;
    private int cantidad;
    private String nombre;


    public  TipoEspacioDeportivoDTO(final int id,final UnidadDeportivaDTO unidadDeportiva,final DeporteDTO deporte
            ,final String espacio,final int cantidad,final String nombre) {
        setId(id);
        setUnidadDeportiva(unidadDeportiva);
        setDeporte(deporte);
        setEspacio(espacio);
        setCantidad(NumericHelper.ZERO);
        setNombre(nombre);
    }

    public  TipoEspacioDeportivoDTO() {
        setEspacio(TextHelper.EMPTY);
        setCantidad(NumericHelper.ZERO);
        setNombre(TextHelper.EMPTY);
    }

    public static final TipoEspacioDeportivoDTO build(){
        return new TipoEspacioDeportivoDTO();
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final UnidadDeportivaDTO getUnidadDeportiva() {
        return unidadDeportiva;
    }

    public final TipoEspacioDeportivoDTO setUnidadDeportiva(final UnidadDeportivaDTO unidadDeportiva) {
        this.unidadDeportiva = ObjectHelper.getObjectHelper().getDefault(unidadDeportiva, UnidadDeportivaDTO.build());
        return this;
    }

    public final DeporteDTO getDeporte() {
        return deporte;
    }

    public final TipoEspacioDeportivoDTO setDeporte(DeporteDTO deporte) {
        this.deporte = ObjectHelper.getObjectHelper().getDefault(deporte, DeporteDTO.build());
        return this;
    }

    public final String getEspacio() {
        return espacio;
    }

    public final TipoEspacioDeportivoDTO setEspacio(final String espacio) {
        this.espacio = TextHelper.applyTrim(espacio);
        return this;
    }

    public final int getCantidad() {
        return cantidad;
    }

    public final TipoEspacioDeportivoDTO setCantidad(int cantidad) {
        this.cantidad = NumericHelper.ZERO;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final  TipoEspacioDeportivoDTO  setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
