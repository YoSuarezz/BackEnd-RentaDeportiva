package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.usuarioingreso.UsuarioDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.usuarioingreso.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTODomainAssembler implements DTODomainAssembler<UsuarioDomain, UsuarioDTO> {

    private static final DTODomainAssembler<UsuarioDomain, UsuarioDTO> instancia =
            new UsuarioDTODomainAssembler();

    private UsuarioDTODomainAssembler(){
        super();
    }

    public static final DTODomainAssembler<UsuarioDomain, UsuarioDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public final UsuarioDomain ensamblarDominio(final UsuarioDTO dto) {
        var usuarioDtoTmp = ObjectHelper.getObjectHelper().getDefault(dto, new UsuarioDTO());
        return UsuarioDomain.crear(usuarioDtoTmp.getId(), usuarioDtoTmp.getUsuario(), usuarioDtoTmp.getContrasena(), usuarioDtoTmp.isActivo());
    }

    @Override
    public UsuarioDTO ensamblarDTO(UsuarioDomain dominio) {
        var usuarioDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio, UsuarioDomain.crear());
        return UsuarioDTO.build().setId(usuarioDomainTmp.getId())
                .setUsuario(usuarioDomainTmp.getUsuario())
                .setContrasena(usuarioDomainTmp.getContrasena())
                .setActivo(usuarioDomainTmp.isActivo());
    }

    @Override
    public final List<UsuarioDTO> ensamblarListaDTO(List<UsuarioDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<UsuarioDomain>());
        var resultados = new ArrayList<UsuarioDTO>();

        for (UsuarioDomain usuarioDomain : listaDominiosTmp) {
            resultados.add(ensamblarDTO(usuarioDomain));
        }
        return resultados;
    }


}

