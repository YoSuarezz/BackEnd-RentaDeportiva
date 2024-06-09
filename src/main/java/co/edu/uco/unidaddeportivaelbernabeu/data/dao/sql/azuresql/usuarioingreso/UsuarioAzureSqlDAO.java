package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.usuarioingreso;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.usuarioingreso.UsuarioDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.usuarioingreso.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UsuarioAzureSqlDAO extends SqlConnection implements UsuarioDAO {


    public UsuarioAzureSqlDAO(Connection connection){
        super(connection);
    }

    public List<UsuarioEntity> consultar(UsuarioEntity entidad) {
        final List<UsuarioEntity> listaPaises = new ArrayList<>();
        final StringBuilder sentenciaSql = new StringBuilder();
        final List<Object> parametros = new ArrayList<>();

        sentenciaSql.append("SELECT id, usuario, contraseña, esta_activo FROM UsuarioIngreso WHERE 1 = 1");

        if (entidad.getId() != 0) {
            sentenciaSql.append(" AND Id = ?");
            parametros.add(entidad.getId());
        }

        if (!entidad.getUsuario().isEmpty()) {
            sentenciaSql.append(" AND usuario LIKE ?");
            parametros.add("%" + entidad.getUsuario() + "%");
        }

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())) {
            int index = 1;
            for (Object parametro : parametros) {
                sentenciaPreparada.setObject(index++, parametro);
            }

            try (final ResultSet resultado = sentenciaPreparada.executeQuery()) {
                while (resultado.next()) {
                    listaPaises.add(new UsuarioEntity
                            (resultado.getInt("id"),
                            resultado.getString("usuario"),
                            resultado.getString("contraseña"),
                            resultado.getBoolean("esta_activo")));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "No ha sido posible consultar la información de los Usuarios. Por favor, inténtelo de nuevo o comuníquese con el administrador.";
            var mensajeTecnico = "Se ha presentado un problema ejecutando la sentancia SQL de consulta de los usuarios en la base de datos Azure SQL";
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = "No ha sido posible llevar a cabo la consulta de la informacion de los usuarios. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Tienda Chepito...";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO ejecutando la sentancia SQL de consulta de los paises en la base de datos Azure SQL";

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaPaises;
    }

}
