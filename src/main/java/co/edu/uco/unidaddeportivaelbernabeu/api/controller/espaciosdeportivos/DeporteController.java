package co.edu.uco.unidaddeportivaelbernabeu.api.controller.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.espaciosdeportivos.DeporteResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.espaciosdeportivos.ConsultarDeportesFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.DeporteDTO;
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
            deporteResponse.setDatos(fachada.ejecutar(paisDtoFilter));
            deporteResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055));
        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            deporteResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(deporteResponse, httpStatusResponse);
    }
}
