package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.azuresql.usuarioingreso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.usuarioingreso.UsuarioDAO;
import co.edu.uco.unidaddeportivaelbernabeu.entity.usuarioingreso.UsuarioEntity;

public final class UsuarioAzureSqlDAO extends SqlConnection implements UsuarioDAO {

    public UsuarioAzureSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<UsuarioEntity> consultar(final UsuarioEntity entidad) {
        final List<UsuarioEntity> listaUsuarios = new ArrayList<>();
        final StringBuilder sentenciaSql = new StringBuilder();
        final List<Object> parametros = new ArrayList<>();

        sentenciaSql.append("SELECT id, usuario, contraseña, esta_activo FROM UsuarioIngreso WHERE 1 = 1");

        if (entidad.getId() != 0) {
            sentenciaSql.append(" AND id = ?");
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
                    listaUsuarios.add(new UsuarioEntity(
                            resultado.getInt("id"),
                            resultado.getString("usuario"),
                            resultado.getString("contraseña"),
                            resultado.getBoolean("esta_activo")));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00106);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00107);
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00108);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00109);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaUsuarios;
    }

    public boolean autenticarUsuario(final String usuario, final String contrasena) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT id FROM UsuarioIngreso WHERE usuario = ? AND contraseña = ? AND esta_activo = 1");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())) {
            sentenciaPreparada.setString(1, usuario);
            sentenciaPreparada.setString(2, contrasena);

            try (final ResultSet resultado = sentenciaPreparada.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00110);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00111);
            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00112);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00113);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }
}
