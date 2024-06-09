package co.edu.uco.unidaddeportivaelbernabeu.api.controller.usuariocontroller;


import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @GetMapping("/dummy")
    public UsuarioDTO getDummy(){
        return UsuarioDTO.build();
    }

}
