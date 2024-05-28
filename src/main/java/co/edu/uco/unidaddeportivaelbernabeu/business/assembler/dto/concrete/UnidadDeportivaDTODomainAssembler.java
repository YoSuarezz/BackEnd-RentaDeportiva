package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;

import java.util.ArrayList;
import java.util.List;

public class UnidadDeportivaDTODomainAssembler implements DTODomainAssembler<UnidadDeportivaDomain, UnidadDeportivaDTO> {

    private  static final DTODomainAssembler <UnidadDeportivaDomain, UnidadDeportivaDTO> instancia = new UnidadDeportivaDTODomainAssembler();

    UnidadDeportivaDTODomainAssembler(){
        super();
    }

    public static final DTODomainAssembler <UnidadDeportivaDomain, UnidadDeportivaDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public UnidadDeportivaDomain ensamblarDominio(UnidadDeportivaDTO dto) {
        var unidadDeportivaDtoTmp = ObjectHelper.getObjectHelper().getDefault(dto, new UnidadDeportivaDTO());
        return UnidadDeportivaDomain.crear(unidadDeportivaDtoTmp.getId(), unidadDeportivaDtoTmp.getNombre(), unidadDeportivaDtoTmp.getCiudad()
                , unidadDeportivaDtoTmp.getDireccion());
    }

    @Override
    public UnidadDeportivaDTO ensamblarDTO(UnidadDeportivaDomain dominio) {
        var unidadDeportivaDtoTmp = ObjectHelper.getObjectHelper().getDefault(dominio, new UnidadDeportivaDomain(dominio.getId(), dominio.getNombre(), dominio.getCiudad()
                , dominio.getDireccion()));
        return UnidadDeportivaDTO.build().setId(unidadDeportivaDtoTmp.getId()).setNombre(unidadDeportivaDtoTmp.getNombre());


    }

    @Override
    public List<UnidadDeportivaDTO> ensamblarListaDTO(List<UnidadDeportivaDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<UnidadDeportivaDomain>());
        var resultados = new ArrayList<UnidadDeportivaDTO>();

        for (UnidadDeportivaDomain unidadDeportivaDomain : listaDominiosTmp){
            resultados.add(ensamblarDTO(unidadDeportivaDomain));
        }
        return resultados;
    }
}
