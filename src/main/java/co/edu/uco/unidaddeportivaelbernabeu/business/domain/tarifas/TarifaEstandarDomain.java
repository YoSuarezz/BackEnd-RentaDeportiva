package co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

import java.time.LocalDateTime;

public class TarifaEstandarDomain {

    private int id;
    private TipoEspacioDeportivoDomain tipoEspacioDeportivo;
    private int precioPorHora;
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public TarifaEstandarDomain(final int id,final TipoEspacioDeportivoDomain tipoEspacioDeportivo,final int precioPorHora,
                                final String nombre, final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin) {
        setId(id);
        setTipoEspacioDeportivo(tipoEspacioDeportivo);
        setPrecioPorHora(precioPorHora);
        setNombre(nombre);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
    }

    public TarifaEstandarDomain(){
        setTipoEspacioDeportivo(TipoEspacioDeportivoDomain.crear());
        setPrecioPorHora(NumericHelper.ZERO);
        setNombre(TextHelper.EMPTY);
        setFechaHoraInicio(null);
        setFechaHoraFin(null);
    }

    public static final TarifaEstandarDomain crear (final int id, final TipoEspacioDeportivoDomain tipoEspacioDeportivo,
                                                    final int precioPorHora, final String nombre, final LocalDateTime fechaHoraInicio, final LocalDateTime fechaHoraFin ){
        return new TarifaEstandarDomain(id, tipoEspacioDeportivo,precioPorHora,nombre,fechaHoraInicio,fechaHoraFin);
    }

    public static final TarifaEstandarDomain crear(){
        return new TarifaEstandarDomain();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoEspacioDeportivoDomain getTipoEspacioDeportivo() {
        return tipoEspacioDeportivo;
    }

    public void setTipoEspacioDeportivo(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        this.tipoEspacioDeportivo = ObjectHelper.getObjectHelper().getDefault(tipoEspacioDeportivo, TipoEspacioDeportivoDomain.crear());
    }

    public int getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(int precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        if (fechaHoraFin != null && fechaHoraInicio != null && fechaHoraInicio.isAfter(fechaHoraFin)) {
            var mensajeTecnico = "La fecha de inicio no puede ser posterior a la fecha de fin.";
            throw new BusinessUDElBernabeuException(mensajeTecnico);
        }
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        if (fechaHoraInicio != null && fechaHoraFin != null && fechaHoraFin.isBefore(fechaHoraInicio)) {
            var mensajeTecnico = "La fecha fin no puede ser anterior a la fecha de inicio";
            throw new BusinessUDElBernabeuException(mensajeTecnico);
        }
        this.fechaHoraFin = fechaHoraFin;
    }
}
