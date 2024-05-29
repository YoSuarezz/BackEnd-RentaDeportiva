package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;


import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.UnidadDeportivaDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.ConsultarUnidadesDeportivasFachada;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.ConsultarUnidadesDeportivas;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.ConsultarUnidadesDeportivasImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.UnidadDeportivaDTO;

import java.util.List;

public class ConsultarUnidadesDeportivasFachadaImpl implements ConsultarUnidadesDeportivasFachada {

    private final DAOFactory factory;

    public ConsultarUnidadesDeportivasFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }
    @Override
    public List<UnidadDeportivaDTO> execute(UnidadDeportivaDTO unidadDeportiva) {
        try {
            var unidadDeportivaDomain = UnidadDeportivaDTODomainAssembler.obtenerInstancia().ensamblarDominio(unidadDeportiva);

            final ConsultarUnidadesDeportivas useCase = new ConsultarUnidadesDeportivasImpl(factory);
            var resultados = useCase.ejecutar(unidadDeportivaDomain);
            return UnidadDeportivaDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (UnidadDeportivaElBernabeuException exception){
            throw exception;
        }catch (Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
