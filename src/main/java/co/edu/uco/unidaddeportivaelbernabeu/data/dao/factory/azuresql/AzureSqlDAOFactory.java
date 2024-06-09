package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.SQLHelper;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.TipoEspacioDeportivoDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.espaciosdeportivos.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.espaciosdeportivos.DeporteAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.espaciosdeportivos.TipoEspacioDeportivoAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.espaciosdeportivos.UnidadDeportivaAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.tarifas.TarifaEstandarAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.usuarioingreso.UsuarioAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.tarifas.TarifaEstandarDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.usuarioingreso.UsuarioDAO;

public final class AzureSqlDAOFactory extends DAOFactory {

	private Connection connection;

	public AzureSqlDAOFactory() {
		obtenerConexion();
	}

	@Override
	protected void obtenerConexion() {
		final String connectionUrl = "jdbc:sqlserver://unidaddeportivaelbernabeu-server.database.windows.net:1433;database=UnidadDeportivaElBernabeu;user=Administrador@unidaddeportivaelbernabeu-server;password=ProyectoDoo*;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00044);

			throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, excepcion);
		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);

			throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, excepcion);
		}
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(connection);
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(connection);
	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(connection);
	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(connection);
	}

	@Override
	public DeporteDAO getDeporteDAO() {
		return new DeporteAzureSqlDAO(connection);
	}

	@Override
	public UnidadDeportivaDAO getUnidadDeportivaDAO() {
		return new UnidadDeportivaAzureSqlDAO(connection);
	}

	@Override
	public TipoEspacioDeportivoDAO getTipoEspacioDeportivoDAO() {
		return new TipoEspacioDeportivoAzureSqlDAO(connection);
	}

	@Override
	public TarifaEstandarDAO getTarifaEstandarDAO() {
		return new TarifaEstandarAzureSqlDAO(connection);
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioAzureSqlDAO(connection);
	}
}