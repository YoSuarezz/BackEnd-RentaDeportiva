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

    protected UnidadDeportivaAzureSqlDAO(Connection connection) {
        super(connection);
    }

    public final List<UnidadDeportivaEntity> consultar(final UnidadDeportivaEntity entidad) {

        final var listaUnidadesDeportivas = new ArrayList<UnidadDeportivaEntity>();
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT id, nombre ");
        sentenciaSql.append("FROM UnidadDeportiva ");
        sentenciaSql.append("ORDER BY nombre ASC ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){

            try(final ResultSet resultados = sentenciaPreparada.executeQuery()){

                while (resultados.next()){
                    UnidadDeportivaEntity unidadDeportivaTmp = UnidadDeportivaEntity.build(resultados.getInt("id"), resultados.getString("nombre"), resultados.getString("ciudad"), resultados.getString("direccion") );
                    listaUnidadesDeportivas.add(unidadDeportivaTmp);
                }

            }catch (SQLException exception){
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);

                throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
            }

        }catch (final DataUDElBernabeuException exception){
            throw exception;
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }

        catch (final Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }

        return listaUnidadesDeportivas;
    }
}
