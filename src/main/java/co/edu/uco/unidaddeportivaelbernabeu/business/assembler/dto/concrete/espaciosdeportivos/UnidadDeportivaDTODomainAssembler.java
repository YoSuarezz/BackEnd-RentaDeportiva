package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.UnidadDeportivaDTO;

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
        return UnidadDeportivaDomain.crear(unidadDeportivaDtoTmp.getId(), unidadDeportivaDtoTmp.getNombre(), unidadDeportivaDtoTmp.getCiudad(), unidadDeportivaDtoTmp.getDireccion());
    }


    @Override
    public UnidadDeportivaDTO ensamblarDTO(UnidadDeportivaDomain dominio) {
        var unidadDeportivaDtoTmp = ObjectHelper.getObjectHelper().getDefault(dominio, new UnidadDeportivaDomain(dominio.getId(), dominio.getNombre(), dominio.getCiudad(), dominio.getDireccion()));
        return UnidadDeportivaDTO.build()
                .setId(unidadDeportivaDtoTmp.getId())
                .setNombre(unidadDeportivaDtoTmp.getNombre())
                .setCiudad(unidadDeportivaDtoTmp.getCiudad())
                .setDireccion(unidadDeportivaDtoTmp.getDireccion());
    }


    @Override
    public List<UnidadDeportivaDTO> ensamblarListaDTO(List<UnidadDeportivaDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<UnidadDeportivaDomain>());
        var resultados = new ArrayList<UnidadDeportivaDTO>();

        if (listaDominiosTmp != null) {
            for (UnidadDeportivaDomain unidadDeportivaDomain : listaDominiosTmp) {
                resultados.add(ensamblarDTO(unidadDeportivaDomain));
            }
        }
        return resultados;
    }
}
