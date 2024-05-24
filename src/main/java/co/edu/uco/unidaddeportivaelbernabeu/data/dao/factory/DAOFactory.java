package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.postgresql.PostgreSqlDAOFactory;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

public abstract class DAOFactory {

	public static final DAOFactory getFactory(final Factory factory) {

		switch (factory) {
			case POSTGRESQL: {
				return new PostgreSqlDAOFactory();
			}
		}
		switch (factory) {
			case ORACLE: {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos ORACLE";

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}
		switch (factory) {
			case MYSQL: {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos MYSQL";

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}
		switch (factory) {
			case AZURE_SQL:  {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos AZURESQL";

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}

		return null;
	}

	protected abstract void obtenerConexion();

	public abstract void iniciarTransaccion();

	public abstract void confirmarTransaccion();

	public abstract void cancelarTransaccion();

	public abstract void cerrarConexion();

	public abstract DeporteDAO getDeporteDAO();
}
