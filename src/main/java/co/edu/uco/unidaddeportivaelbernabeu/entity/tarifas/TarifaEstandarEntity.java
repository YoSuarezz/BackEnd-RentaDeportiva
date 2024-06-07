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
    private int precioPorHora;
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public TarifaEstandarEntity(int id, TipoEspacioDeportivoEntity tipoEspacioDeportivo, String nombre,
                                int precioPorHora, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setNombre(nombre);
        setPrecioPorHora(precioPorHora);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarEntity(int id) {
        setId(id);
        setTipoEspacioDeportivo(TipoEspacioDeportivoEntity.build());
        setNombre(TextHelper.EMPTY);
        setPrecioPorHora(NumericHelper.ZERO);
    }

    public TarifaEstandarEntity(int id, TipoEspacioDeportivoEntity tipoEspacioDeportivo, String nombre) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setNombre(nombre);
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
                                                   final String nombre, final int precioPorHora,
                                                   final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin){
        return new TarifaEstandarEntity(id, tipoEspacioDeportivo, nombre, precioPorHora, fechaHoraInicio, fechaHoraFin);
    }

    public static final TarifaEstandarEntity build(){
        return new TarifaEstandarEntity(NumericHelper.ZERO);
    }

    public static final TarifaEstandarEntity build(final int id, String nombre, final TipoEspacioDeportivoEntity tipoEspacioDeportivo){
        return new TarifaEstandarEntity(id, tipoEspacioDeportivo, nombre);
    }
}
