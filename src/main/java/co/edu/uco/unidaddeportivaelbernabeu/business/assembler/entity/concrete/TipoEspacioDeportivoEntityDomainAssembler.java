package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.espaciosdeportivos.DeporteEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.espaciosdeportivos.UnidadDeportivaEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.espaciosdeportivos.DeporteEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.TipoEspacioDeportivoEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.espaciosdeportivos.UnidadDeportivaEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoEspacioDeportivoEntityDomainAssembler implements EntityDomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoEntity> {

    private static final EntityDomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoEntity> instancia = new TipoEspacioDeportivoEntityDomainAssembler();

    private static final EntityDomainAssembler<UnidadDeportivaDomain, UnidadDeportivaEntity> unidadDeportivaAssembler = UnidadDeportivaEntityDomainAssembler.obtenerInstancia();

    private static final EntityDomainAssembler<DeporteDomain, DeporteEntity> deporteAssembler = new DeporteEntityDomainAssembler();

    public TipoEspacioDeportivoEntityDomainAssembler(){
        super();
    }

    public static final EntityDomainAssembler<TipoEspacioDeportivoDomain, TipoEspacioDeportivoEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public TipoEspacioDeportivoDomain ensamblarDominio(TipoEspacioDeportivoEntity entity) {
        var tipoEspacioDeportivoTemp = ObjectHelper.getObjectHelper().getDefault(entity, TipoEspacioDeportivoEntity.build());
        var unidadDeportivaDomain = unidadDeportivaAssembler.ensamblarDominio(tipoEspacioDeportivoTemp.getUnidadDeportiva());
        var deporteDomain = deporteAssembler.ensamblarDominio(tipoEspacioDeportivoTemp.getDeporte());
        return TipoEspacioDeportivoDomain.crear(tipoEspacioDeportivoTemp.getId(),
                unidadDeportivaDomain, deporteDomain,
                tipoEspacioDeportivoTemp.getEspacio(),
                tipoEspacioDeportivoTemp.getCantidad(),
                tipoEspacioDeportivoTemp.getNombre());
    }

    @Override
    public TipoEspacioDeportivoEntity ensamblarEntidad(TipoEspacioDeportivoDomain dominio) {
        var tipoEspacioDeportivoDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, TipoEspacioDeportivoDomain.crear());
        var unidadDeportivaEntity = UnidadDeportivaEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivoDomainTemp.getUnidadDeportiva());
        var deporteEnity = DeporteEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivoDomainTemp.getDeporte());
        return TipoEspacioDeportivoEntity.build(tipoEspacioDeportivoDomainTemp.getId(),
                unidadDeportivaEntity,
                deporteEnity,
                tipoEspacioDeportivoDomainTemp.getEspacio(),
                tipoEspacioDeportivoDomainTemp.getCantidad(),
                tipoEspacioDeportivoDomainTemp.getNombre());
    }

    @Override
    public List<TipoEspacioDeportivoDomain> ensamblarListaDominios(List<TipoEspacioDeportivoEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaEntidades, new ArrayList<TipoEspacioDeportivoEntity>());
        var resultados = new ArrayList<TipoEspacioDeportivoDomain>();

        for (TipoEspacioDeportivoEntity tipoEspacioDeportivoEntity : listaEntidadesTmp){
            var unidadDeportivaDomainTmp = ensamblarDominio(tipoEspacioDeportivoEntity);
            resultados.add(unidadDeportivaDomainTmp);
        }
        return resultados;
    }

}
