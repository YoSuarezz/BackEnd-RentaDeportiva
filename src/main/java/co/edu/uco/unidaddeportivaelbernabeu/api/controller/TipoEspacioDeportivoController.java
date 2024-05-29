package co.edu.uco.unidaddeportivaelbernabeu.api.controller;


import co.edu.uco.unidaddeportivaelbernabeu.api.response.TipoEspacioDeportivoResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.ConsultarTipoEspacioDeportivoFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tiposespaciosdeportivos")
public class TipoEspacioDeportivoController {

    @GetMapping("/dummy")
    public TipoEspacioDeportivoDTO getDummy(){
        return TipoEspacioDeportivoDTO.build();
    }

    @GetMapping
    public ResponseEntity<TipoEspacioDeportivoResponse> consultar(
            @RequestParam(required = false, defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "") DeporteDTO deporte,
            @RequestParam(required = false, defaultValue = "") UnidadDeportivaDTO unidaddeportiva,
            @RequestParam(required = false, defaultValue = "") String espacio,
            @RequestParam(required = false, defaultValue = "") String cantidad,
            @RequestParam(required = false, defaultValue = "") String nombre){
        TipoEspacioDeportivoResponse tipoEspacioDeportivoResponse = TipoEspacioDeportivoResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var tipoEspacioDeportivoDtoFilter = TipoEspacioDeportivoDTO.build().setId(NumericHelper.convertToInt(id))
                    .setDeporte(deporte).setUnidadDeportiva(unidaddeportiva).setEspacio(espacio).setCantidad(NumericHelper.convertToInt(cantidad))
                    .setNombre(nombre);
            final ConsultarTipoEspacioDeportivoFachadaImpl fachada = new ConsultarTipoEspacioDeportivoFachadaImpl();
            tipoEspacioDeportivoResponse.setDatos(fachada.execute(tipoEspacioDeportivoDtoFilter));
            tipoEspacioDeportivoResponse.getMensajes().add("TiposDeEspaciosDeportivos consultados exitosamente !");
        } catch (final UnidadDeportivaElBernabeuException exception) {
            exception.printStackTrace();
            tipoEspacioDeportivoResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        }catch (final Exception exception) {
            exception.printStackTrace();
            tipoEspacioDeportivoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(tipoEspacioDeportivoResponse, httpStatusResponse);

    }
    }





