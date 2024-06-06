package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.TipoEspacioDeportivoDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;

import java.util.ArrayList;
import java.util.List;

public class TarifaEstandarDTODomainAssembler implements DTODomainAssembler<TarifaEstandarDomain, TarifaEstandarDTO> {

    private static final DTODomainAssembler<TarifaEstandarDomain, TarifaEstandarDTO> instancia = new TarifaEstandarDTODomainAssembler();

    private static final DTODomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoDTO> tipoEspacioDeportivoAssembler = TipoEspacioDeportivoDTODomainAssembler.obtenerInstancia();

    private TarifaEstandarDTODomainAssembler(){
        super();
    }

    public static final DTODomainAssembler<TarifaEstandarDomain, TarifaEstandarDTO> obtenerInstancia(){
        return instancia;
    }

    @Override
    public TarifaEstandarDomain ensamblarDominio(TarifaEstandarDTO dto) {
        var tarifaEstandarDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, new TarifaEstandarDTO());
        var tipoEspacioDeportivoDomain = tipoEspacioDeportivoAssembler.ensamblarDominio(tarifaEstandarDTOTemp.getTipoEspacioDeportivo());

        return TarifaEstandarDomain.crear(tarifaEstandarDTOTemp.getId(),
                tipoEspacioDeportivoDomain,
                tarifaEstandarDTOTemp.getPrecioPorHora(),
                tarifaEstandarDTOTemp.getNombre(),
                tarifaEstandarDTOTemp.getFechaHoraInicio(),
                tarifaEstandarDTOTemp.getFechaHoraFin());
    }

    @Override
    public TarifaEstandarDTO ensamblarDTO(TarifaEstandarDomain dominio) {
        var tarifaEstandarDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio, TarifaEstandarDomain.crear());
        var tipoEspacioDeportivoDTO = tipoEspacioDeportivoAssembler.ensamblarDTO(tarifaEstandarDomainTmp.getTipoEspacioDeportivo());

        return TarifaEstandarDTO.build().setId(tarifaEstandarDomainTmp.getId())
                .setTipoEspacioDeportivo(tipoEspacioDeportivoDTO)
                .setPrecioPorHora(tarifaEstandarDomainTmp.getPrecioPorHora())
                .setNombre(tarifaEstandarDomainTmp.getNombre())
                .setFechaHoraInicio(tarifaEstandarDomainTmp.getFechaHoraInicio())
                .setFechaHoraFin(tarifaEstandarDomainTmp.getFechaHoraFin());
    }

    @Override
    public List<TarifaEstandarDTO> ensamblarListaDTO(List<TarifaEstandarDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaDominios, new ArrayList<TarifaEstandarDomain>());
        var resultados = new ArrayList<TarifaEstandarDTO>();

        for(TarifaEstandarDomain tarifaEstandarDomain : listaDominiosTmp){
            resultados.add(ensamblarDTO(tarifaEstandarDomain));
        }
        return resultados;
    }
}
