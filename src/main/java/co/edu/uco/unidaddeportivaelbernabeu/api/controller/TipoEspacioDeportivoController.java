package co.edu.uco.unidaddeportivaelbernabeu.api.controller;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.TipoEspacioDeportivoResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.ConsultarTipoEspacioDeportivoFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.EliminarTipoEspacioDeportivoFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.RegistrarTipoEspacioDeportivoFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.ActualizarTipoEspacioDeportivoFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipos-espacios-deportivos")
public class TipoEspacioDeportivoController {

    @GetMapping("/dummy")
    public TipoEspacioDeportivoDTO getDummy() {
        return TipoEspacioDeportivoDTO.build();
    }

    @PostMapping
    public ResponseEntity<TipoEspacioDeportivoResponse> crear(@RequestBody TipoEspacioDeportivoDTO tipoEspacioDeportivo) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoEspacioDeportivoResponse = TipoEspacioDeportivoResponse.build();

        try {
            var facade = new RegistrarTipoEspacioDeportivoFachadaImpl();
            facade.ejecutar(tipoEspacioDeportivo);
            tipoEspacioDeportivoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00056));
        } catch (final UnidadDeportivaElBernabeuException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoEspacioDeportivoResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
            tipoEspacioDeportivoResponse.getMensajes().add(mensajeUsuario);
            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(tipoEspacioDeportivoResponse, httpStatusCode);
    }

    @PutMapping
    public ResponseEntity<TipoEspacioDeportivoResponse> actualizar(@RequestBody TipoEspacioDeportivoDTO tipoEspacioDeportivo) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoEspacioDeportivoResponse = TipoEspacioDeportivoResponse.build();

        try {
            var facade = new ActualizarTipoEspacioDeportivoFachadaImpl();
            facade.ejecutar(tipoEspacioDeportivo);
            tipoEspacioDeportivoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00096));
        } catch (final UnidadDeportivaElBernabeuException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoEspacioDeportivoResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
            tipoEspacioDeportivoResponse.getMensajes().add(mensajeUsuario);
            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(tipoEspacioDeportivoResponse, httpStatusCode);
    }

    @GetMapping
    public ResponseEntity<TipoEspacioDeportivoResponse> consultar(@RequestParam(required = false) Integer id) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var tipoEspacioDeportivoResponse = new TipoEspacioDeportivoResponse();

        try {
            TipoEspacioDeportivoDTO tipoEspacioDeportivoDTO;

            if (id != null) {
                tipoEspacioDeportivoDTO = TipoEspacioDeportivoDTO.build().setId(id);
            } else {
                tipoEspacioDeportivoDTO = TipoEspacioDeportivoDTO.build();
            }

            var facade = new ConsultarTipoEspacioDeportivoFachadaImpl();
            tipoEspacioDeportivoResponse.setDatos(facade.ejecutar(tipoEspacioDeportivoDTO));
            tipoEspacioDeportivoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057));
        } catch (final UnidadDeportivaElBernabeuException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            tipoEspacioDeportivoResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
            tipoEspacioDeportivoResponse.getMensajes().add(mensajeUsuario);
            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(tipoEspacioDeportivoResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            EliminarTipoEspacioDeportivoFachadaImpl eliminarFachada = new EliminarTipoEspacioDeportivoFachadaImpl();
            eliminarFachada.ejecutar(id);
            return ResponseEntity.ok(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00097));
        } catch (BusinessUDElBernabeuException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (final Exception exception) {
            return ResponseEntity.internalServerError().body(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
        }
    }
}
