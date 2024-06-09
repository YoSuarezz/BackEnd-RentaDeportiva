package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private DateHelper() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static LocalDateTime parseFromString(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00068);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    public static void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            throw new BusinessUDElBernabeuException(mensajeUsuario);
        }
    }
}
