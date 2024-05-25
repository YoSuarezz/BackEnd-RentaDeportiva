package co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.DataUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.DeporteDAO;
import co.edu.uco.unidaddeportivaelbernabeu.data.dao.sql.SqlConnection;
import co.edu.uco.unidaddeportivaelbernabeu.entity.DeporteEntity;

public final class DeportePostgreSqlDAO extends SqlConnection implements DeporteDAO {

    public DeportePostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void crear(final DeporteEntity entidad) {
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO Deporte(Nombre) ");
        sentenciaSql.append("VALUES(?)");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){
            sentenciaPreparada.setString(1, entidad.getNombre());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032, entidad.getNombre());
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032, entidad.getNombre());
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public final void actualizar(final DeporteEntity entidad) {
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE Deporte");
        sentenciaSql.append("SET Nombre = ? ");
        sentenciaSql.append("WHERE id = ? ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){

            sentenciaPreparada.setString(1, entidad.getNombre());
            sentenciaPreparada.setInt(2, entidad.getId());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033, entidad.getNombre());
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033, entidad.getNombre());
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026, entidad.getNombre());

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }

    }

    @Override
    public final void eliminar(final int id) {
        final var sentenciaSql = new StringBuilder();
        sentenciaSql.append("DELETE FROM Deporte ");
        sentenciaSql.append("WHERE id = ?");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){

            sentenciaPreparada.setInt(1, id);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00028);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public final List<DeporteEntity> consultar(final DeporteEntity entidad) {

        final var listaDeportes = new ArrayList<DeporteEntity>();
        final var sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT id, nombre ");
        sentenciaSql.append("FROM Deporte ");
        sentenciaSql.append("ORDER BY nombre ASC ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){

            try(final ResultSet resultados = sentenciaPreparada.executeQuery()){

                while (resultados.next()){
                    DeporteEntity deporteTmp = DeporteEntity.build(resultados.getInt("id"), resultados.getString("nombre"));
                    listaDeportes.add(deporteTmp);
                }

            }catch (SQLException exception){
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);

                throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
            }

        }catch (final DataUDElBernabeuException exception){
            throw exception;
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }

        catch (final Exception exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);

            throw new DataUDElBernabeuException(mensajeTecnico, mensajeUsuario, exception);
        }

        return listaDeportes;
    }

}