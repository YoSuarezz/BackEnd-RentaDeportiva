package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.postgresql;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.SQLHelper;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.postgresql.DeportePostgreSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public final class PostgreSqlDAOFactory extends DAOFactory {

	private Connection connection;

	public PostgreSqlDAOFactory() {
		obtenerConexion();
	}

	@Override
	protected void obtenerConexion() {
		final String connectionUrl = ""; //TODO: Ingresar string base de datos propia
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema tratando de obtener la conexión con la base de datos wednesday en el servidor de bases de datos wednesday.database.windows.net. Por favor revise la traza de errores para identificar y solucionar el problema...";

			throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, excepcion);
		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de obtener la conexión con la base de datos wednesday en el servidor de bases de datos wednesday.database.windows.net. Por favor revise la traza de errores para identificar y solucionar el problema...";

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
		return new DeportePostgreSqlDAO(connection);
	}

	public static void main(String[] args) {
		try {
			DAOFactory factory = DAOFactory.getFactory(Factory.POSTGRESQL);

			System.out.println("Iniciando transacción...");
			factory.iniciarTransaccion();

			System.out.println("Creando deporte aleatoriamente");
			factory.getDeporteDAO().crear(DeporteEntity.build(0, "Futbol-" + UUID.randomUUID().toString()));

			System.out.println("Consultamos deportes: ");
			var resultados = factory.getDeporteDAO().consultar(DeporteEntity.build(0));

			for (DeporteEntity DeporteEntity : resultados) {
				System.out.println("id: " + DeporteEntity.getId() + ", nombre: " + DeporteEntity.getNombre());
			}

			System.out.println("Confirmando transacción...");
			factory.confirmarTransaccion();
			System.out.println("Cerrando conexión...");
			factory.cerrarConexion();
		} catch (final Exception excepcion) {
			excepcion.printStackTrace();
		}
	}

}
