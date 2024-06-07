package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers;


import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Converts a string date to a LocalDateTime object using the standard format.
     * @param dateStr the string to convert.
     * @return the LocalDateTime object.
     * @throws DateTimeParseException if the string cannot be parsed.
     */
    public static LocalDateTime parseFromString(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            var mensajeUsuario = "La fecha proporcioanda esta vacia o es nula";
            var mensajeTecnico = "La fecha proporcioanda esta vacia o es nula";
            throw new BusinessUDElBernabeuException(mensajeUsuario,mensajeTecnico);
        }
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    /**
     * Validates that the start date is before the end date.
     * @param startDate the start date.
     * @param endDate the end date.
     * @throws IllegalArgumentException if the start date is after the end date.
     */
    public static void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
    }


}

