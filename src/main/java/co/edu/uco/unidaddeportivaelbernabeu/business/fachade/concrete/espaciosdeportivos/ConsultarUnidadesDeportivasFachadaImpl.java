package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.espaciosdeportivos;


import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos.UnidadDeportivaDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.espaciosdeportivos.ConsultarUnidadesDeportivasImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.UnidadDeportivaDTO;

import java.util.List;

public class ConsultarUnidadesDeportivasFachadaImpl implements FacadeWithReturn<UnidadDeportivaDTO, List<UnidadDeportivaDTO>> {

    private final DAOFactory factory;

    public ConsultarUnidadesDeportivasFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public List<UnidadDeportivaDTO> ejecutar(final UnidadDeportivaDTO dto) {
        factory.iniciarTransaccion();
        try {
            var useCase = new ConsultarUnidadesDeportivasImpl(factory);
            var unidadDeportivaDomain = UnidadDeportivaDTODomainAssembler.obtenerInstancia().ensamblarDominio(dto);
            var resultadosDomain = useCase.ejecutar(unidadDeportivaDomain);
            return UnidadDeportivaDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultadosDomain);

        }catch (UnidadDeportivaElBernabeuException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (Exception exception){
            factory.cancelarTransaccion();

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
