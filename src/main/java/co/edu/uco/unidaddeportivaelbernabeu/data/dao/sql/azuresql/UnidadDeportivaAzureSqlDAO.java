package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.UnidadDeportivaDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.entity.UnidadDeportivaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UnidadDeportivaAzureSqlDAO extends SqlConnection implements UnidadDeportivaDAO {

    public UnidadDeportivaAzureSqlDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<UnidadDeportivaEntity> consultar(UnidadDeportivaEntity entidad) {
        List<UnidadDeportivaEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nombre, ciudad, direccion FROM UnidadDeportiva WHERE 1=1 ");

        List<Object> parametros = new ArrayList<>();

        if (entidad.getId() > 0) {
            sql.append("AND id = ? ");
            parametros.add(entidad.getId());
        }

        if (entidad.getNombre() != null && !entidad.getNombre().isEmpty()) {
            sql.append("AND nombre LIKE ? ");
            parametros.add("%" + entidad.getNombre() + "%");
        }

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                preparedStatement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UnidadDeportivaEntity unidadDeportiva = new UnidadDeportivaEntity(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("ciudad"),
                            resultSet.getString("direccion")
                    );
                    resultados.add(unidadDeportiva);
                }
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, excepcion);
        }

        return resultados;
    }
}
