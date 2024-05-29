package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.TipoEspacioDeportivoDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.RegistrarTipoEspacioDeportivoFachada;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.RegistrarTipoEspacioDeportivoImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.TipoEspacioDeportivoDTO;

public class RegistrarTipoEspacioDeportivoFachadaImpl implements RegistrarTipoEspacioDeportivoFachada {

    private DAOFactory daoFactory;

    public RegistrarTipoEspacioDeportivoFachadaImpl(){
        daoFactory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }
    @Override
    public void ejecutar(TipoEspacioDeportivoDTO tipoEspacioDeportivo) {
        daoFactory.iniciarTransaccion();

        try {
            var usecase = new RegistrarTipoEspacioDeportivoImpl(daoFactory);
            var tipoEspacioDeportivoDomain = TipoEspacioDeportivoDTODomainAssembler.obtenerInstancia().ensamblarDominio(tipoEspacioDeportivo);
            usecase.ejecutar(tipoEspacioDeportivoDomain);

            daoFactory.confirmarTransaccion();
        } catch (final UnidadDeportivaElBernabeuException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "";
            var mensajeTecnico = "";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            daoFactory.cerrarConexion();
        }

    }

}
