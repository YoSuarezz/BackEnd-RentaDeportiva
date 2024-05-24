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

    @PostMapping
    public ResponseEntity<DeporteResponse> crear(@RequestBody DeporteDTO pais){
        DeporteResponse deporteResponse = DeporteResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            deporteResponse.getDatos().add(pais);
            deporteResponse.getMensajes().add("Deporte registrado correctamente");
        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add("se ha presentado un problema inesperado tratando de crear el deporte");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(deporteResponse,httpStatusResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeporteResponse> eliminar(@PathVariable int id){
        DeporteResponse deporteResponse = DeporteResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            deporteResponse.getDatos().add(DeporteDTO.build().setId(id));
            deporteResponse.getMensajes().add("Deporte ha sido eliminado correctamente");
        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add("se ha presentado un problema inesperado tratando de eliminar el deporte");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(deporteResponse, httpStatusResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeporteResponse> modificar(@PathVariable int id, @RequestBody DeporteDTO deporte){
        DeporteResponse deporteResponse = DeporteResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            deporte.setId(id);
            deporteResponse.getDatos().add(deporte);
            deporteResponse.getMensajes().add("Deporte modificado correctamente");

        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add("se ha presentado un problema inesperado tratando de modificar el deporte");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(deporteResponse, httpStatusResponse);
    }
}
