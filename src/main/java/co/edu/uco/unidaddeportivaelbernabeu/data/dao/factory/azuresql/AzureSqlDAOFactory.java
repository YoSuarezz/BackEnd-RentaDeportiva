package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.SQLHelper;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.DAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.DeporteAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.UnidadDeportivaAzureSqlDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;
import co.edu.uco.unidaddeportivaelbernabeu.entity.UnidadDeportivaEntity;

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

	public static void main(String[] args) {
		try {
			DAOFactory factory = DAOFactory.getFactory(Factory.AZURE_SQL);

			System.out.println("Iniciando transacción...");
			factory.iniciarTransaccion();

			System.out.println("Consultando deportes... ");
			DeporteDAO deporteDAO = factory.getDeporteDAO();
			// Crear un objeto DeporteEntity con los criterios de búsqueda
			DeporteEntity deporteCriterio = DeporteEntity.build().setId(0); // ejemplo búsqueda por nombre
			List<DeporteEntity> resultados = deporteDAO.consultar(deporteCriterio);
			for (DeporteEntity deporte : resultados) {
				System.out.println("ID: " + deporte.getId() + " - Nombre: " + deporte.getNombre());
			}

			System.out.println("Consultando unidad deportiva... ");
			UnidadDeportivaDAO unidadDeportivaDAO = factory.getUnidadDeportivaDAO();

			// Crear un objeto UnidadDeportivaEntity con los criterios de búsqueda
			UnidadDeportivaEntity unidadDeportivaCriterio = UnidadDeportivaEntity.build().setId(1); // ejemplo búsqueda por nombre

			// Ejecutar la consulta
			List<UnidadDeportivaEntity> resultadosUDB = unidadDeportivaDAO.consultar(unidadDeportivaCriterio);

			// Imprimir los resultados
			for (UnidadDeportivaEntity unidadDeportiva : resultadosUDB) {
				System.out.println("ID: " + unidadDeportiva.getId() + " - Nombre: " + unidadDeportiva.getNombre() + ", Ciudad: " + unidadDeportiva.getCiudad() + ", Dirección: " + unidadDeportiva.getDireccion());
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
