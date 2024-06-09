package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas.MonedaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas.MonedaEntity;

import java.util.ArrayList;
import java.util.List;

public final class MonedaEntityDomainAssembler implements EntityDomainAssembler <MonedaDomain, MonedaEntity> {
    private static final EntityDomainAssembler <MonedaDomain, MonedaEntity> instancia = new MonedaEntityDomainAssembler();

    public static final EntityDomainAssembler<MonedaDomain, MonedaEntity> obtenerIntancia() {
        return instancia;
    }

    @Override
    public final MonedaDomain ensamblarDominio(final MonedaEntity entity) {
        var monedaEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, MonedaEntity.build(NumericHelper.ZERO));
        return MonedaDomain.crear(monedaEntityTemp.getId(), monedaEntityTemp.getNombre());
    }

    @Override
    public final MonedaEntity ensamblarEntidad(final MonedaDomain dominio) {
        var monedaDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, MonedaDomain.crear());
        return MonedaEntity.build(monedaDomainTemp.getId(), monedaDomainTemp.getNombre());
    }

    @Override
    public List<MonedaDomain> ensamblarListaDominios(List<MonedaEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper().getDefault(listaEntidades, new ArrayList<MonedaEntity>());
        var resultados = new ArrayList<MonedaDomain>();

        for (MonedaEntity monedaEntity : listaEntidadesTmp){
            var monedaDomainTmp = ensamblarDominio(monedaEntity);
            resultados.add(monedaDomainTmp);
        }
        return resultados;
    }
}

