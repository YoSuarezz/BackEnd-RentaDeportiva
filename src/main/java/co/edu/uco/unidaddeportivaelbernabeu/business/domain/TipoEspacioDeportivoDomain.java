package co.edu.uco.unidaddeportivaelbernabeu.business.domain;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;


public class TipoEspacioDeportivoDomain {
    private int id;
    private UnidadDeportivaDomain unidadDeportiva;
    private DeporteDomain deporte;
    private String espacio;
    private int cantidad;
    private String nombre;


    public  TipoEspacioDeportivoDomain(final int id,final UnidadDeportivaDomain unidadDeportiva,final DeporteDomain deporte,
                                      final String espacio,final int cantidad,final String nombre) {
        setId(id);
        setUnidadDeportiva(unidadDeportiva);
        setDeporte(deporte);
        setEspacio(espacio);
        setCantidad(cantidad);
        setNombre(nombre);
    }

    public TipoEspacioDeportivoDomain() {
        setUnidadDeportiva(UnidadDeportivaDomain.crear());
        setDeporte(DeporteDomain.crear());
        setEspacio(TextHelper.EMPTY);
        setCantidad(NumericHelper.ZERO);
        setNombre(TextHelper.EMPTY);
    }

    public static final TipoEspacioDeportivoDomain crear (final int id, final UnidadDeportivaDomain unidadDeportiva
    ,DeporteDomain deporte, String espacio, int cantidad, String nombre) {
        return new TipoEspacioDeportivoDomain(id,unidadDeportiva,deporte,espacio,cantidad,nombre);

    }

    public static final TipoEspacioDeportivoDomain crear (){
        return new TipoEspacioDeportivoDomain();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public void setUnidadDeportiva(UnidadDeportivaDomain unidadDeportiva) {
        this.unidadDeportiva = ObjectHelper.getObjectHelper().getDefault(unidadDeportiva, UnidadDeportivaDomain.crear());
    }

    public DeporteDomain getDeporte() {
        return deporte;
    }

    public void setDeporte(DeporteDomain deporte) {
        this.deporte = ObjectHelper.getObjectHelper().getDefault(deporte, DeporteDomain.crear());
    }

    public UnidadDeportivaDomain getUnidadDeportiva() {
        return unidadDeportiva;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = TextHelper.applyTrim(espacio);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}
