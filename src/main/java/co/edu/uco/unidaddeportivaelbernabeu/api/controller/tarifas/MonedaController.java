package co.edu.uco.unidaddeportivaelbernabeu.api.controller.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.tarifas.MonedaResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas.ConsultarMonedasFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.MonedaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/monedas")
public class MonedaController {
    @GetMapping("/dummy")
    public MonedaDTO getDummy(){
        return MonedaDTO.build();
    }

    @GetMapping
    public ResponseEntity<MonedaResponse> consultar(
            @RequestParam(required = false,defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "")String nombre){
        MonedaResponse monedaResponse = MonedaResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var monedaDtoFilter = MonedaDTO.build().setId(NumericHelper.convertToInt(id)).setNombre(nombre);
            final ConsultarMonedasFachadaImpl fachada = new ConsultarMonedasFachadaImpl();
            monedaResponse.setDatos(fachada.ejecutar(monedaDtoFilter));
            monedaResponse.getMensajes().add("Monedas consultadas exitosamente");
        } catch (final UnidadDeportivaElBernabeuException exception){
            exception.printStackTrace();
            monedaResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            monedaResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(monedaResponse, httpStatusResponse);
    }
}
