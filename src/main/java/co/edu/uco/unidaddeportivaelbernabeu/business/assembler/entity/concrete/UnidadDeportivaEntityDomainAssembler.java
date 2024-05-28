package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.UnidadDeportivaDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.UnidadDeportivaEntity;

import java.util.ArrayList;
import java.util.List;

public class UnidadDeportivaEntityDomainAssembler implements EntityDomainAssembler <UnidadDeportivaDomain, UnidadDeportivaEntity> {

    private  static final EntityDomainAssembler<UnidadDeportivaDomain, UnidadDeportivaEntity> instancia = new UnidadDeportivaEntityDomainAssembler();

    public static final EntityDomainAssembler <UnidadDeportivaDomain, UnidadDeportivaEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public final UnidadDeportivaDomain ensamblarDominio(final UnidadDeportivaEntity entity) {
        var unidadDeportivaEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, UnidadDeportivaEntity.build(0));
        return UnidadDeportivaDomain.crear(unidadDeportivaEntityTemp.getId(), unidadDeportivaEntityTemp.getNombre(),
                unidadDeportivaEntityTemp.getCiudad(), unidadDeportivaEntityTemp.getDireccion());
    }

    @Override
    public final UnidadDeportivaEntity ensamblarEntidad(final UnidadDeportivaDomain dominio) {
        var unidadDeportivaDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, UnidadDeportivaDomain.crear());
        return UnidadDeportivaEntity.build(unidadDeportivaDomainTemp.getId(), unidadDeportivaDomainTemp.getNombre()
                , unidadDeportivaDomainTemp.getCiudad(), unidadDeportivaDomainTemp.getDireccion());
    }

    @Override
    public List<UnidadDeportivaDomain> ensamblarListaDominios(List<UnidadDeportivaEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaEntidades, new ArrayList<UnidadDeportivaEntity>());
        var resultados = new ArrayList<UnidadDeportivaDomain>();

        for (UnidadDeportivaEntity unidadDeportivaEntity : listaEntidadesTmp){
            var unidadDeportivaDomainTmp = ensamblarDominio(unidadDeportivaEntity);
            resultados.add(unidadDeportivaDomainTmp);
        }
        return resultados;
    }
}
