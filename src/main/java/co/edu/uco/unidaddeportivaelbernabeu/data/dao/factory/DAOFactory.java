package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.TipoEspacioDeportivoDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.azuresql.AzureSqlDAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.MonedaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.TarifaEstandarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.usuarioingreso.UsuarioDAO;

import java.util.Objects;

public abstract class DAOFactory {

	public static final DAOFactory getFactory(final Factory factory) {

        if (Objects.requireNonNull(factory) == Factory.AZURE_SQL) {
            return new AzureSqlDAOFactory();
        }
        if (factory == Factory.ORACLE) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        }
        if (factory == Factory.MYSQL) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje((CodigoMensaje.M00041));

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        }
        if (factory == Factory.POSTGRESQL) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        }
        if (factory == Factory.SQL_SERVER) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
        }

		return null;
	}

	protected abstract void obtenerConexion();

	public abstract void iniciarTransaccion();

	public abstract void confirmarTransaccion();

	public abstract void cancelarTransaccion();

	public abstract void cerrarConexion();

	public abstract DeporteDAO getDeporteDAO();

	public abstract UnidadDeportivaDAO getUnidadDeportivaDAO();

	public abstract TipoEspacioDeportivoDAO getTipoEspacioDeportivoDAO();

    public abstract TarifaEstandarDAO getTarifaEstandarDAO();

    public abstract UsuarioDAO getUsuarioDAO();

    public abstract MonedaDAO getMonedaDAO();
}
