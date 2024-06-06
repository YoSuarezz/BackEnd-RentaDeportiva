package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.espaciosdeportivos.UnidadDeportivaEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.TarifaEstandarDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.TarifaEstandarEntity;

import java.util.ArrayList;
import java.util.List;

public class TarifaEstandarEntityDomainAssembler implements EntityDomainAssembler<TarifaEstandarDomain, TarifaEstandarEntity> {

    private static final EntityDomainAssembler<TarifaEstandarDomain, TarifaEstandarEntity> instancia = new TarifaEstandarEntityDomainAssembler();

    private static final EntityDomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoEntity> tipoEspacioDeportivoAssembler = new TipoEspacioDeportivoEntityDomainAssembler().obtenerInstancia();

    private TarifaEstandarEntityDomainAssembler(){
        super();
    }

    public static final EntityDomainAssembler<TarifaEstandarDomain, TarifaEstandarEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public TarifaEstandarDomain ensamblarDominio(TarifaEstandarEntity entity) {
        var tarifaEstandarTemp = ObjectHelper.getObjectHelper().getDefault(entity, TarifaEstandarEntity.build());
        var tipoEspacioDeportivoDomain = tipoEspacioDeportivoAssembler.ensamblarDominio(tarifaEstandarTemp.getTipoEspacioDeportivo());
        return TarifaEstandarDomain.crear(tarifaEstandarTemp.getId(),
                tipoEspacioDeportivoDomain,
                tarifaEstandarTemp.getPrecioPorHora(),
                tarifaEstandarTemp.getNombre(),
                tarifaEstandarTemp.getFechaHoraInicio(),
                tarifaEstandarTemp.getFechaHoraFin());

    }

    @Override
    public TarifaEstandarEntity ensamblarEntidad(TarifaEstandarDomain dominio) {
        var tarifaEstandarDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, TarifaEstandarDomain.crear());
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tarifaEstandarDomainTemp.getTipoEspacioDeportivo());
        return TarifaEstandarEntity.build(tarifaEstandarDomainTemp.getId(),
                tipoEspacioDeportivoEntity,
                tarifaEstandarDomainTemp.getNombre(),
                tarifaEstandarDomainTemp.getPrecioPorHora(),
                tarifaEstandarDomainTemp.getFechaHoraInicio(),
                tarifaEstandarDomainTemp.getFechaHoraFin());
    }

    @Override
    public List<TarifaEstandarDomain> ensamblarListaDominios(List<TarifaEstandarEntity> listaEntidades) {
       var listaEntidadesTmp = ObjectHelper.getObjectHelper()
               .getDefault(listaEntidades, new ArrayList<TarifaEstandarEntity>());
       var resultados = new ArrayList<TarifaEstandarDomain>();

       for (TarifaEstandarEntity tarifaEstandarEntity : listaEntidadesTmp) {
           var tipoEspacioDeportivoTmp = ensamblarDominio(tarifaEstandarEntity);
           resultados.add(tipoEspacioDeportivoTmp);
       }
       return resultados;
    }
}
