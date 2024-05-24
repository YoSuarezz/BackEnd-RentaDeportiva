package co.edu.uco.unidaddeportivaelbernabeu.business.fachade.concrete;

import co.edu.uco.unidaddeportivaelbernabeu.business.assembler.dto.concrete.DeporteDTODomainAssembler;
import co.edu.uco.unidaddeportivaelbernabeu.business.fachade.RegistrarDeporteFachada;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.RegistrarDeporte;
import co.edu.uco.unidaddeportivaelbernabeu.business.usecase.concrete.RegistrarDeporteImpl;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.BusinessUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.dto.DeporteDTO;

public final class RegistrarDeporteFachadaImpl implements RegistrarDeporteFachada {

    private DAOFactory factory;

    public RegistrarDeporteFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public final void ejecutar(DeporteDTO deporte) {
        try {
            factory.iniciarTransaccion();
            var deporteDomain = DeporteDTODomainAssembler.obtenerInstancia().ensamblarDominio(deporte);
            RegistrarDeporte useCase = new RegistrarDeporteImpl(factory);
            useCase.ejecutar(deporteDomain);
            factory.confirmarTransaccion();
        } catch (UnidadDeportivaElBernabeuException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información del nuevo país";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de registrar el nuevo país en el método ejecutar de la clase RegistrarDeporteFachadaImpl";

            throw new BusinessUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
