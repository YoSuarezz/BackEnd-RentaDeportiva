package co.edu.uco.unidaddeportivaelbernabeu.api.controller.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.tarifas.TarifaEstandarResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas.CrearTarifaEstandarFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarifasEstandar")
public class TarifaEstandarController {

    @GetMapping("/dummy")
    public TarifaEstandarDTO getDummy() {
        return TarifaEstandarDTO.build();
    }

    @PostMapping
    public ResponseEntity<TarifaEstandarResponse> crear(@RequestBody TarifaEstandarDTO tarifaEstandar) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var tarifaEstandarResponse = TarifaEstandarResponse.build();

        try {
            var facade = new CrearTarifaEstandarFachadaImpl();
            facade.ejecutar(tarifaEstandar);
            tarifaEstandarResponse.getMensajes().add("Se creó correctamente la tarifa estándar para el espacio deportivo.");

        } catch (final UnidadDeportivaElBernabeuException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tarifaEstandarResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
            tarifaEstandarResponse.getMensajes().add(mensajeUsuario);

            excepcion.printStackTrace();
        }
        return new ResponseEntity<>(tarifaEstandarResponse, httpStatusCode);
    }
}
