package co.edu.uco.unidaddeportivaelbernabeu.api.controller;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.deporte.DeporteResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.ConsultarDeportesFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/deportes")
public class DeporteController {

    @GetMapping("/dummy")
    public DeporteDTO getDummy(){
        return DeporteDTO.build();
    }

    @GetMapping
    public ResponseEntity<DeporteResponse> consultar(
            @RequestParam(required = false,defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "")String nombre){
        DeporteResponse deporteResponse = DeporteResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var paisDtoFilter = DeporteDTO.build().setId(NumericHelper.convertToInt(id)).setNombre(nombre);
            final ConsultarDeportesFachadaImpl fachada = new ConsultarDeportesFachadaImpl();
            deporteResponse.setDatos(fachada.execute(paisDtoFilter));
            deporteResponse.getMensajes().add("Deportes consultados exitosamente");
        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add("se ha presentado un problema inesperado");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(deporteResponse, httpStatusResponse);
    }
}
