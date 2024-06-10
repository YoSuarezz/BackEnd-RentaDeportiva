package co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.DateHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TarifaEstandarDTO {

    private int id;
    private TipoEspacioDeportivoDTO tipoEspacioDeportivo;
    private MonedaDTO moneda;
    private int precioPorHora;
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHoraInicio;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHoraFin;

    public TarifaEstandarDTO(final int id,final TipoEspacioDeportivoDTO tipoEspacioDeportivo,final MonedaDTO moneda,final int precioPorHora,final String nombre,final LocalDateTime fechaHoraInicio,final LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setMoneda(moneda);
        setPrecioPorHora(precioPorHora);
        setNombre(nombre);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarDTO(){
        setNombre(TextHelper.applyTrim(nombre));
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
        setPrecioPorHora(NumericHelper.ZERO);
    }

    public static final TarifaEstandarDTO build(){
        return new TarifaEstandarDTO();
    }

    public int getId() {
        return id;
    }

    public final TarifaEstandarDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final TipoEspacioDeportivoDTO getTipoEspacioDeportivo() {
        return tipoEspacioDeportivo;
    }

    public final TarifaEstandarDTO setTipoEspacioDeportivo(TipoEspacioDeportivoDTO tipoEspacioDeportivo) {
        this.tipoEspacioDeportivo = ObjectHelper.getObjectHelper().getDefault(tipoEspacioDeportivo, TipoEspacioDeportivoDTO.build());
        return this;
    }

    public MonedaDTO getMoneda() {
        return moneda;
    }

    public final TarifaEstandarDTO setMoneda(MonedaDTO moneda) {
        this.moneda = ObjectHelper.getObjectHelper().getDefault(moneda, MonedaDTO.build());
        return this;
    }

    public int getPrecioPorHora() {
        return precioPorHora;
    }

    public final TarifaEstandarDTO setPrecioPorHora(int precioPorHora) {
        this.precioPorHora = precioPorHora;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public final TarifaEstandarDTO setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public TarifaEstandarDTO setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
        DateHelper.validateDates(this.fechaHoraInicio, this.fechaHoraFin);
        return this;
    }


    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public TarifaEstandarDTO setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
        DateHelper.validateDates(this.fechaHoraInicio, this.fechaHoraFin);
        return this;
    }
}