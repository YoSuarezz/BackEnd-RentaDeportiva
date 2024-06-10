package co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

import java.time.LocalDateTime;

public class TarifaEstandarDomain {

    private int id;
    private TipoEspacioDeportivoDomain tipoEspacioDeportivo;
    private MonedaDomain moneda;
    private int precioPorHora;
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public TarifaEstandarDomain(final int id,final TipoEspacioDeportivoDomain tipoEspacioDeportivo, MonedaDomain moneda, final int precioPorHora,
                                final String nombre, final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setMoneda(moneda);
        setPrecioPorHora(precioPorHora);
        setNombre(nombre);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarDomain(){
        setTipoEspacioDeportivo(TipoEspacioDeportivoDomain.crear());
        setMoneda(MonedaDomain.crear());
        setPrecioPorHora(NumericHelper.ZERO);
        setNombre(TextHelper.EMPTY);
        setFechaHoraInicio(null);
        setFechaHoraFin(null);
    }

    public static final TarifaEstandarDomain crear (final int id, final TipoEspacioDeportivoDomain tipoEspacioDeportivo, final MonedaDomain moneda,
                                                    final int precioPorHora, final String nombre, final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin ){
        return new TarifaEstandarDomain(id, tipoEspacioDeportivo, moneda ,precioPorHora,nombre,fechaHoraInicio,fechaHoraFin);
    }

    public static final TarifaEstandarDomain crear(){
        return new TarifaEstandarDomain();
    }



    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final TipoEspacioDeportivoDomain getTipoEspacioDeportivo() {
        return tipoEspacioDeportivo;
    }

    public final void setTipoEspacioDeportivo(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        this.tipoEspacioDeportivo = ObjectHelper.getObjectHelper().getDefault(tipoEspacioDeportivo, TipoEspacioDeportivoDomain.crear());
    }

    public final MonedaDomain getMoneda() {
        return moneda;
    }

    public final void setMoneda(MonedaDomain moneda) {
        this.moneda = ObjectHelper.getObjectHelper().getDefault(moneda, MonedaDomain.crear());
    }

    public final int getPrecioPorHora() {
        return precioPorHora;
    }

    public final void setPrecioPorHora(int precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public final void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        if (fechaHoraFin != null && fechaHoraInicio != null && fechaHoraInicio.isAfter(fechaHoraFin)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00090);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public final LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public final void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        if (fechaHoraInicio != null && fechaHoraFin != null && fechaHoraFin.isBefore(fechaHoraInicio)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00095);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        this.fechaHoraFin = fechaHoraFin;
    }
}
