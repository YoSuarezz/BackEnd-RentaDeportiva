package co.edu.uco.unidaddeportivaelbernabeu.api.controller.usuariocontroller;

import co.edu.uco.unidaddeportivaelbernabeu.api.response.usuarioingreso.UsuarioResponse;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.usuarioingreso.AutenticarUsuarioFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.usuarioingreso.ConsultarUsuariosFachadaImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @GetMapping("/dummy")
    public UsuarioDTO getDummy() {
        return UsuarioDTO.build();
    }

    @PostMapping("/autenticar")
    public ResponseEntity<UsuarioResponse> autenticar(@RequestBody UsuarioDTO usuario) {
        UsuarioResponse usuarioResponse = UsuarioResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final AutenticarUsuarioFachadaImpl fachada = new AutenticarUsuarioFachadaImpl();
            boolean autenticado = fachada.ejecutar(usuario);
            usuarioResponse.getDatos().add(usuario);
            usuarioResponse.getMensajes().add(autenticado ? MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00136) : MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00137) );
            if (!autenticado) {
                httpStatusResponse = HttpStatus.UNAUTHORIZED;
            }
        } catch (final UnidadDeportivaElBernabeuException exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(usuarioResponse, httpStatusResponse);
    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> consultar(
            @RequestParam(required = false, defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "") String usuario) {
        UsuarioResponse usuarioResponse = UsuarioResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var usuarioDtoFilter = UsuarioDTO.build().setId(NumericHelper.convertToInt(id)).setUsuario(usuario);
            final ConsultarUsuariosFachadaImpl fachada = new ConsultarUsuariosFachadaImpl();
            usuarioResponse.setDatos(fachada.ejecutar(usuarioDtoFilter));
            usuarioResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00138));
        } catch (final UnidadDeportivaElBernabeuException exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054));
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(usuarioResponse, httpStatusResponse);
    }
}
