package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    // Definimos un formato de fecha y hora estándar
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Método para parsear una cadena de texto en un objeto LocalDateTime
    public static LocalDateTime parseFromString(String dateStr) {
        // Verificamos si la cadena es nula o está vacía
        if (dateStr == null || dateStr.trim().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00068);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        // Parseamos la cadena de texto en un objeto LocalDateTime usando el formato definido
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    public static void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        // Verificamos que ambas fechas no sean nulas y que la fecha de inicio no sea posterior a la fecha de fin
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
    }
}
