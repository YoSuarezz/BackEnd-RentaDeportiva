package co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.usuarioingreso.UsuarioDomain;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;
import co.edu.uco.unidaddeportivaelbernabeu.entity.usuarioingreso.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class UsuarioEntityDomainAssembler implements EntityDomainAssembler <UsuarioDomain, UsuarioEntity>{

    private static final EntityDomainAssembler <UsuarioDomain, UsuarioEntity> instancia = new UsuarioEntityDomainAssembler();

    public static final EntityDomainAssembler <UsuarioDomain, UsuarioEntity> obtenerInstancia(){
        return instancia;
    }


    @Override
    public UsuarioDomain ensamblarDominio(final UsuarioEntity entity) {
        var usuarioEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, UsuarioEntity.build(0));
        return UsuarioDomain.crear(usuarioEntityTemp.getId(), usuarioEntityTemp.getUsuario(), usuarioEntityTemp.getContrasena(), usuarioEntityTemp.isActivo());
    }

    @Override
    public UsuarioEntity ensamblarEntidad(final UsuarioDomain dominio) {
        var usuarioEntityTemp = ObjectHelper.getObjectHelper().getDefault(dominio, UsuarioDomain.crear());
        return UsuarioEntity.build(usuarioEntityTemp.getId(), usuarioEntityTemp.getUsuario(), usuarioEntityTemp.getContrasena(), usuarioEntityTemp.isActivo());
    }

    @Override
    public List<UsuarioDomain> ensamblarListaDominios(List<UsuarioEntity> listaEntidades) {
        var listaEntidaesTmp = ObjectHelper.getObjectHelper().getDefault(listaEntidades, new ArrayList<UsuarioEntity>());
        var resultados = new ArrayList<UsuarioDomain>();

        for (UsuarioEntity paisEntity : listaEntidaesTmp) {
            var usuarioDomainTmp = ensamblarDominio(paisEntity);
            resultados.add(usuarioDomainTmp);
        }
        return resultados;
    }
}
