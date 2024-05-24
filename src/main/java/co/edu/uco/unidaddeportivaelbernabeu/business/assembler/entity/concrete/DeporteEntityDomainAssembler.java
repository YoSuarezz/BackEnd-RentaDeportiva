package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.DeporteDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

import java.util.ArrayList;
import java.util.List;

public final class DeporteEntityDomainAssembler implements EntityDomainAssembler <DeporteDomain, DeporteEntity> {

private  static final EntityDomainAssembler <DeporteDomain, DeporteEntity> instancia = new DeporteEntityDomainAssembler();

    public static final EntityDomainAssembler <DeporteDomain, DeporteEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public final DeporteDomain ensamblarDominio(final DeporteEntity entity) {
        var deporteEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, DeporteEntity.build(0));
        return DeporteDomain.crear(deporteEntityTemp.getId(), deporteEntityTemp.getNombre());
    }

    @Override
    public final DeporteEntity ensamblarEntidad(final DeporteDomain dominio) {
        var deporteDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, DeporteDomain.crear());
        return DeporteEntity.build(deporteDomainTemp.getId(), deporteDomainTemp.getNombre());
    }

    @Override
    public List<DeporteDomain> ensamblarListaDominios(List<DeporteEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaEntidades, new ArrayList<DeporteEntity>());
        var resultados = new ArrayList<DeporteDomain>();

        for (DeporteEntity deporteEntity : listaEntidadesTmp){
            var deporteDomainTmp = ensamblarDominio(deporteEntity);
            resultados.add(deporteDomainTmp);
        }
        return resultados;
    }
}
