package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.TipoEspacioDeportivoDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.ConsultarTipoEspacioDeportivoImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;

import java.util.List;

public class ConsultarTipoEspacioDeportivoFachadaImpl implements FacadeWithReturn<TipoEspacioDeportivoDTO, List<TipoEspacioDeportivoDTO>> {

    private final DAOFactory factory;


    public ConsultarTipoEspacioDeportivoFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public List<TipoEspacioDeportivoDTO> ejecutar(final TipoEspacioDeportivoDTO dto) {
        factory.iniciarTransaccion();
        try {
            var useCase = new ConsultarTipoEspacioDeportivoImpl(factory);
            var tipoEspacioDeportivoDomain = TipoEspacioDeportivoDTODomainAssembler.obtenerInstancia().ensamblarDominio(dto);
            var resultadosDomain = useCase.ejecutar(tipoEspacioDeportivoDomain);
            return TipoEspacioDeportivoDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultadosDomain);

        }catch (UnidadDeportivaElBernabeuException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (Exception exception){
            factory.cancelarTransaccion();
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        }finally {
            factory.cerrarConexion();
        }
    }
}
