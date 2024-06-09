package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.MonedaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.MonedaDTO;

import java.util.ArrayList;
import java.util.List;

public final class MonedaDTODomainAssembler implements DTODomainAssembler<MonedaDomain, MonedaDTO> {

    private  static final DTODomainAssembler <MonedaDomain, MonedaDTO> instancia = new MonedaDTODomainAssembler();

    MonedaDTODomainAssembler(){
        super();
    }

    public static final DTODomainAssembler <MonedaDomain, MonedaDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public MonedaDomain ensamblarDominio(MonedaDTO dto) {
        var monedaDtoTmp = ObjectHelper.getObjectHelper().getDefault(dto, new MonedaDTO());
        return MonedaDomain.crear(monedaDtoTmp.getId(), monedaDtoTmp.getNombre());
    }

    @Override
    public MonedaDTO ensamblarDTO(MonedaDomain dominio) {
        var monedaDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio, new MonedaDomain(dominio.getId(), dominio.getNombre()));
        return MonedaDTO.build().setId(monedaDomainTmp.getId()).setNombre(monedaDomainTmp.getNombre());

    }

    @Override
    public final List<MonedaDTO> ensamblarListaDTO(final List<MonedaDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<MonedaDomain>());
        var resultados = new ArrayList<MonedaDTO>();

        for (MonedaDomain monedaDomain : listaDominiosTmp){
            resultados.add(ensamblarDTO(monedaDomain));
        }
        return resultados;
    }

}

