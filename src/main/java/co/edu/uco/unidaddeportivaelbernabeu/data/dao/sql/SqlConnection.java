package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql;

import java.sql.Connection;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.SQLHelper;

public class SqlConnection {

    private Connection connection;

    protected SqlConnection(final Connection connection) {
        setConnection(connection);
    }

    private final void setConnection(final Connection connection) {
        if (!SQLHelper.isOpen(connection)) {
            var mensajeUsuario= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico="No es posible crear el DAO deseado dado que la conexion SQL esta cerrada";
            throw new DataUDElBernabeuException(mensajeTecnico,mensajeUsuario);
        }
        this.connection = connection;
    }

    protected final Connection getConnection() {
        return connection;
    }
}
