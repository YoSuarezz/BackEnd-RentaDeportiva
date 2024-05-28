package co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.enums.Factory;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.factory.azuresql.AzureSqlDAOFactory;
import org.springframework.context.support.MessageSourceAccessor;

public abstract class DAOFactory {

	public static final DAOFactory getFactory(final Factory factory) {

		switch (factory) {
			case AZURE_SQL: {
				return new AzureSqlDAOFactory();
			}
		}
		switch (factory) {
			case ORACLE: {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040);

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}
		switch (factory) {
			case MYSQL: {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje((CodigoMensaje.M00041));

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}
		switch (factory) {
			case POSTGRESQL:  {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042);

				throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario);
			}
		}
		switch (factory){
			case SQL_SERVER:  {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043);

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
