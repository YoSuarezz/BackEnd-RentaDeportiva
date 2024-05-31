package co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.entity.concrete.TipoEspacioDeportivoEntityDomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.domain.TipoEspacioDeportivoDomain;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarTipoEspacioDeportivoImpl implements UseCaseWithReturn<TipoEspacioDeportivoDomain, List<TipoEspacioDeportivoDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoEspacioDeportivoImpl(final DAOFactory factory) {this.factory = factory;}


    @Override
    public List<TipoEspacioDeportivoDomain> ejecutar(TipoEspacioDeportivoDomain tipoEspacioDeportivo) {
        var tipoEspacioDeportivoEntity = TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(tipoEspacioDeportivo);
        var resultados = factory.getTipoEspacioDeportivoDAO().consultar(tipoEspacioDeportivoEntity);

        return TipoEspacioDeportivoEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(resultados);
    }
}
