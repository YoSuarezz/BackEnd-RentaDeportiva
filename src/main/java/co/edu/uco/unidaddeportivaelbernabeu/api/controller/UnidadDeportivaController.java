package co.edu.uco.unidaddeportivaelbernabeu.api.controller;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.UnidadDeportivaResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.ConsultarUnidadesDeportivasFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unidadesDeportivas")
public class UnidadDeportivaController {

    @GetMapping("/dummy")
    public UnidadDeportivaDTO getDummy() {
        return UnidadDeportivaDTO.build();
    }

    @GetMapping
    public ResponseEntity<UnidadDeportivaResponse> consultar(
            @RequestParam(required = false, defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "") String nombre,
            @RequestParam(required = false, defaultValue = "") String ciudad,
            @RequestParam(required = false, defaultValue = "") String direccion) {
        UnidadDeportivaResponse unidadDeportivaResponse = UnidadDeportivaResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var unidadDeportivaDtoFilter = UnidadDeportivaDTO.build()
                    .setId(NumericHelper.convertToInt(id))
                    .setNombre(nombre)
                    .setCiudad(ciudad)
                    .setDireccion(direccion);

            final ConsultarUnidadesDeportivasFachadaImpl fachada = new ConsultarUnidadesDeportivasFachadaImpl();
            unidadDeportivaResponse.setDatos(fachada.execute(unidadDeportivaDtoFilter));
            unidadDeportivaResponse.getMensajes().add("Unidades deportivas consultadas exitosamente");
        } catch (final UnidadDeportivaElBernabeuException exception) {
            exception.printStackTrace();
            unidadDeportivaResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception) {
            exception.printStackTrace();
            unidadDeportivaResponse.getMensajes().add("Se ha presentado un problema inesperado");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(unidadDeportivaResponse, httpStatusResponse);
    }
}

