package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.TipoEspacioDeportivoDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.FacadeWithoutReturn;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.ActualizarTipoEspacioDeportivoImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;

public class ActualizarTipoEspacioDeportivoFachadaImpl implements FacadeWithoutReturn<TipoEspacioDeportivoDTO> {

    private final DAOFactory factory;

    public ActualizarTipoEspacioDeportivoFachadaImpl() {
        this.factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public void ejecutar(TipoEspacioDeportivoDTO tipoEspacioDeportivo) {
        factory.iniciarTransaccion();

        try {
            var usecase = new ActualizarTipoEspacioDeportivoImpl(factory);
            var tipoEspacioDeportivoDomain = TipoEspacioDeportivoDTODomainAssembler.obtenerInstancia().ensamblarDominio(tipoEspacioDeportivo);
            usecase.ejecutar(tipoEspacioDeportivoDomain);

            factory.confirmarTransaccion();
        } catch (final UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (final Exception exception) {
            factory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un error tratando de editar el tipo de espacio deportivo.";
            var mensajeTecnico = "Se ha presentado un problema inesperado tratando de editar la información del tipo de espacio deportivo en el método ejecutar de la clase ActualizarTipoEspacioDeportivoFachadaImpl. Por favor revise la traza completa del problema.";

            throw new BusinessUDElBernabeuException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            factory.cerrarConexion();
        }
    }
}
