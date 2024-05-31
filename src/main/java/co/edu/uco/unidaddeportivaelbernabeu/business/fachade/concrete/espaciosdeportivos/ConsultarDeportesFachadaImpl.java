package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.espaciosdeportivos.DeporteDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.UseCaseWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.espaciosdeportivos.ConsultarDeportesImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos.DeporteDTO;

import java.util.List;

public final class ConsultarDeportesFachadaImpl implements UseCaseWithReturn<DeporteDTO,List<DeporteDTO>> {

    private DAOFactory factory;

    public ConsultarDeportesFachadaImpl(){
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public final List<DeporteDTO> ejecutar(final DeporteDTO deporte) {
        factory.iniciarTransaccion();
        try {
            var deporteDomain = DeporteDTODomainAssembler.obtenerInstancia().ensamblarDominio(deporte);

            final ConsultarDeportesImpl useCase = new ConsultarDeportesImpl(factory);
            var resultados = useCase.ejecutar(deporteDomain);
            return DeporteDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (UnidadDeportivaElBernabeuException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (Exception exception){
            factory.cancelarTransaccion();
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}