package co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.DateHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;

import java.time.LocalDateTime;

public class TarifaEstandarEntity {

    private int id;
    private TipoEspacioDeportivoEntity tipoEspacioDeportivo;
    private MonedaEntity moneda;
    private int precioPorHora;
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public TarifaEstandarEntity(final int id,final TipoEspacioDeportivoEntity tipoEspacioDeportivo,final MonedaEntity moneda, final String nombre,
                                final int precioPorHora,final LocalDateTime fechaHoraInicio,final LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setMoneda(moneda);
        setNombre(nombre);
        setPrecioPorHora(precioPorHora);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarEntity(final int id) {
        setId(id);
        setTipoEspacioDeportivo(TipoEspacioDeportivoEntity.build());
        setMoneda(MonedaEntity.build());
        setNombre(TextHelper.EMPTY);
        setPrecioPorHora(NumericHelper.ZERO);
    }

    public TarifaEstandarEntity(int id, TipoEspacioDeportivoEntity tipoEspacioDeportivo,MonedaEntity moneda, String nombre) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setMoneda(moneda);
        setNombre(nombre);
    }

    public static final TarifaEstandarEntity build(final int id) {
        return new TarifaEstandarEntity(id);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public TipoEspacioDeportivoEntity getTipoEspacioDeportivo() {
        return tipoEspacioDeportivo;
    }

    public final TarifaEstandarEntity setTipoEspacioDeportivo(final TipoEspacioDeportivoEntity tipoEspacioDeportivo) {
        this.tipoEspacioDeportivo = ObjectHelper.getObjectHelper().getDefault(tipoEspacioDeportivo, TipoEspacioDeportivoEntity.build());
        return this;
    }

    public MonedaEntity getMoneda() {
        return moneda;
    }

    public final TarifaEstandarEntity setMoneda(final MonedaEntity moneda) {
        this.moneda = ObjectHelper.getObjectHelper().getDefault(moneda, MonedaEntity.build());
        return this;
    }

    public int getPrecioPorHora() {
        return precioPorHora;
    }

    public final void setPrecioPorHora(final int precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
        DateHelper.validateDates(this.fechaHoraInicio, this.fechaHoraFin);
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
        DateHelper.validateDates(this.fechaHoraInicio, this.fechaHoraFin);
    }

    public static final TarifaEstandarEntity build(final int id, final TipoEspacioDeportivoEntity tipoEspacioDeportivo,
                                                   final MonedaEntity moneda, final String nombre, final int precioPorHora,
                                                   final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin){
        return new TarifaEstandarEntity(id, tipoEspacioDeportivo,moneda, nombre, precioPorHora, fechaHoraInicio, fechaHoraFin);
    }

    public static final TarifaEstandarEntity build(){
        return new TarifaEstandarEntity(NumericHelper.ZERO);
    }

    public static final TarifaEstandarEntity build(final int id, String nombre, final TipoEspacioDeportivoEntity tipoEspacioDeportivo, final MonedaEntity moneda){
        return new TarifaEstandarEntity(id, tipoEspacioDeportivo, moneda, nombre);
    }
}