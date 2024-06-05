package co.edu.uco.unidaddeportivaelbernabeu.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.unidaddeportivaelbernabeu.dto.UsuarioIngresoDTO;
import co.edu.uco.unidaddeportivaelbernabeu.service.UsuarioIngresoService;

@RestController
@RequestMapping("/api/v1/usuario-ingreso")
public class UsuarioIngresoController {

    private final UsuarioIngresoService usuarioIngresoService;

    public UsuarioIngresoController(UsuarioIngresoService usuarioIngresoService) {
        this.usuarioIngresoService = usuarioIngresoService;
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UsuarioIngresoDTO usuarioIngreso) {
        boolean esValido = usuarioIngresoService.validarUsuario(usuarioIngreso);
        return new ResponseEntity<>(esValido, HttpStatus.OK);
    }
}
