package co.edu.uco.unidaddeportivaelbernabeu.entity;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class TipoEspacioDeportivoEntity {

    private int id;
    private UnidadDeportivaEntity unidadDeportiva;
    private DeporteEntity deporte;
    private String espacio;
    private int cantidad;
    private String nombre;

    //Constructores
    public   TipoEspacioDeportivoEntity(final int id,final UnidadDeportivaEntity unidadDeportiva
            ,final DeporteEntity deporte,final String espacio,final int cantidad,final String nombre) {
        setId(id);
        setUnidadDeportiva(unidadDeportiva);
        setDeporte(deporte);
        setEspacio(espacio);
        setCantidad(cantidad);
        setNombre(nombre);
    }


    public TipoEspacioDeportivoEntity(final int id) {
        setId(id);
        setUnidadDeportiva(UnidadDeportivaEntity.build());
        setDeporte(DeporteEntity.build());
        setEspacio(TextHelper.EMPTY);
        setCantidad(NumericHelper.ZERO);
        setNombre(TextHelper.EMPTY);

    }


    //Setters y getters
    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final UnidadDeportivaEntity getUnidadDeportiva() {
        return unidadDeportiva;
    }

    public final TipoEspacioDeportivoEntity setUnidadDeportiva(final UnidadDeportivaEntity unidadDeportiva) {
        this.unidadDeportiva = ObjectHelper.getObjectHelper().getDefault(unidadDeportiva, UnidadDeportivaEntity.build());
        return this;
    }

    public final DeporteEntity getDeporte() {
        return deporte;
    }

    public final void setDeporte(DeporteEntity deporte) {
        this.deporte = deporte;
    }

    public final String getEspacio() {
        return espacio;
    }

    public final void setEspacio(final String espacio) {
        this.espacio = TextHelper.applyTrim(espacio);
    }

    public final int getCantidad() {
        return cantidad;
    }

    public final void setCantidad(final int cantidad) {
        this.cantidad = cantidad;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }


    //builders
    public static final TipoEspacioDeportivoEntity build(final int id){
        return new TipoEspacioDeportivoEntity(id);
    }

    public static final TipoEspacioDeportivoEntity build(final int id, final UnidadDeportivaEntity unidadDeportiva
            , DeporteEntity deporte, String espacio, int cantidad, String nombre){
        return new TipoEspacioDeportivoEntity(id,unidadDeportiva,deporte,espacio,cantidad,nombre);
    }

    public static final TipoEspacioDeportivoEntity build(){
        return new TipoEspacioDeportivoEntity(NumericHelper.ZERO);
    }

}
