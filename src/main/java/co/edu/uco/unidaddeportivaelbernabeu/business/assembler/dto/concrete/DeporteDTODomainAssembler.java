package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;

import java.util.ArrayList;
import java.util.List;

public final class DeporteDTODomainAssembler implements DTODomainAssembler <DeporteDomain, DeporteDTO> {

    private  static final DTODomainAssembler <DeporteDomain, DeporteDTO> instancia = new DeporteDTODomainAssembler();

    DeporteDTODomainAssembler(){
        super();
    }

    public static final DTODomainAssembler <DeporteDomain, DeporteDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public DeporteDomain ensamblarDominio(DeporteDTO dto) {
        var deporteDtoTmp = ObjectHelper.getObjectHelper().getDefault(dto, new DeporteDTO());
        return DeporteDomain.crear(deporteDtoTmp.getId(), deporteDtoTmp.getNombre());
    }

    @Override
    public DeporteDTO ensamblarDTO(DeporteDomain dominio) {
        var deporteDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio, new DeporteDomain(dominio.getId(), dominio.getNombre()));
        return DeporteDTO.build().setId(deporteDomainTmp.getId()).setNombre(deporteDomainTmp.getNombre());

    }

    @Override
    public final List<DeporteDTO> ensamblarListaDTO(final List<DeporteDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<DeporteDomain>());
        var resultados = new ArrayList<DeporteDTO>();

        for (DeporteDomain deporteDomain : listaDominiosTmp){
            resultados.add(ensamblarDTO(deporteDomain));
        }
        return resultados;
    }

}
