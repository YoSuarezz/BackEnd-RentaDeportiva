package co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TarifaEstandarDTO {

    private int id;
    private TipoEspacioDeportivoDTO tipoEspacioDeportivo;
    private int precioPorHora;
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TarifaEstandarDTO(int id, TipoEspacioDeportivoDTO tipoEspacioDeportivo, int precioPorHora, String nombre, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setPrecioPorHora(precioPorHora);
        setNombre(nombre);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarDTO(){
        setNombre(TextHelper.applyTrim(nombre));
        setFechaHoraInicio(LocalDateTime.now());
        setFechaHoraFin(LocalDateTime.now());
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
        return this;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public TarifaEstandarDTO setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
        return this;
    }

    public String getFormattedFechaHoraInicio() {
        return FORMATTER.format(fechaHoraInicio);
    }

    public String getFormattedFechaHoraFin() {
        return FORMATTER.format(fechaHoraFin);
    }
}
