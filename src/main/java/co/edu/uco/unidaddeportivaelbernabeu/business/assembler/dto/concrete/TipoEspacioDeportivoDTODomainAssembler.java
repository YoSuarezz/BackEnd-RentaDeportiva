package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos.DeporteDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos.UnidadDeportivaDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.DeporteDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.UnidadDeportivaDTO;

import java.util.ArrayList;
import java.util.List;

public class TipoEspacioDeportivoDTODomainAssembler implements DTODomainAssembler <TipoEspacioDeportivoDomain, TipoEspacioDeportivoDTO> {

    private static final DTODomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoDTO> instancia = new TipoEspacioDeportivoDTODomainAssembler();

    private static final DTODomainAssembler<UnidadDeportivaDomain, UnidadDeportivaDTO> unidadDeportivaAssembler = UnidadDeportivaDTODomainAssembler.obtenerInstancia();

    private static final DTODomainAssembler<DeporteDomain, DeporteDTO> deporteAssembler = DeporteDTODomainAssembler.obtenerInstancia();

    private TipoEspacioDeportivoDTODomainAssembler(){
        super();
    }

    public static final  DTODomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public TipoEspacioDeportivoDomain ensamblarDominio(TipoEspacioDeportivoDTO dto) {
        var tipoespacioDtoTmp = ObjectHelper.getObjectHelper().getDefault(dto, new TipoEspacioDeportivoDTO());
        var unidadDeportivaDomain = unidadDeportivaAssembler.ensamblarDominio(tipoespacioDtoTmp.getUnidadDeportiva());
        var deporteDomain = deporteAssembler.ensamblarDominio(tipoespacioDtoTmp.getDeporte());

        return TipoEspacioDeportivoDomain.crear(tipoespacioDtoTmp.getId(),
                unidadDeportivaDomain,
                deporteDomain,
                tipoespacioDtoTmp.getEspacio(),
                tipoespacioDtoTmp.getCantidad(),
                tipoespacioDtoTmp.getNombre());
    }

    @Override
    public TipoEspacioDeportivoDTO ensamblarDTO(TipoEspacioDeportivoDomain dominio) {
        var tipoEspacioDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio, TipoEspacioDeportivoDomain.crear());
        var unidadDeportivaDTO = unidadDeportivaAssembler.ensamblarDTO(tipoEspacioDomainTmp.getUnidadDeportiva());
        var deporteDTO = deporteAssembler.ensamblarDTO(tipoEspacioDomainTmp.getDeporte());

        return TipoEspacioDeportivoDTO.build().setId(tipoEspacioDomainTmp.getId())
                .setUnidadDeportiva(unidadDeportivaDTO)
                .setDeporte(deporteDTO)
                .setEspacio(tipoEspacioDomainTmp.getEspacio())
                .setCantidad(tipoEspacioDomainTmp.getCantidad())
                .setNombre(tipoEspacioDomainTmp.getNombre());
    }

    @Override
    public List<TipoEspacioDeportivoDTO> ensamblarListaDTO(List<TipoEspacioDeportivoDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<TipoEspacioDeportivoDomain>());
        var resultados = new ArrayList<TipoEspacioDeportivoDTO>();

        for (TipoEspacioDeportivoDomain tipoespacioDomain : listaDominiosTmp){
            resultados.add(ensamblarDTO(tipoespacioDomain));
        }
        return resultados;
    }
}

