package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.tarifas.TarifaEstandarDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.tarifas.ConsultarTarifasEstandarImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas.TarifaEstandarDTO;

import java.util.List;

public class ConsultarTarifasEstandarFachadaImpl implements FacadeWithReturn<TarifaEstandarDTO, List<TarifaEstandarDTO>> {

    private final DAOFactory factory;

    public ConsultarTarifasEstandarFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public List<TarifaEstandarDTO> ejecutar(final TarifaEstandarDTO dto) {
        factory.iniciarTransaccion();
        try {
            var useCase = new ConsultarTarifasEstandarImpl(factory);
            var tarifaEstandarDomain = TarifaEstandarDTODomainAssembler.obtenerInstancia().ensamblarDominio(dto);
            var resultadosDomain = useCase.ejecutar(tarifaEstandarDomain);
            return TarifaEstandarDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultadosDomain);

        } catch (UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
