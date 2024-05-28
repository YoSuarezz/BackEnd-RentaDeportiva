package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.impl;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.CrosscuttingUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalog;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.Mensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

public final class MessageCatalogExternalService implements MessageCatalog {

	private final Map<String, Mensaje> mensajes = new HashMap<>();

	@Override
	public final void inicializar() {
		mensajes.clear();
		//mensajes.put(CodigoMensaje.M00007.getIdentificador(),
				new Mensaje(CodigoMensaje.M00007, "La transacción se ha completado de forma satisfactoria...");
		mensajes.put(CodigoMensaje.M00023.getIdentificador(), new Mensaje(CodigoMensaje.M00023,
				"Se ha presentado un problema tratando de realizar un insert de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00024.getIdentificador(), new Mensaje(CodigoMensaje.M00024,
				"Se ha presentado un problema INESPERADO tratando de realizar un insert de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00025.getIdentificador(), new Mensaje(CodigoMensaje.M00025,
				"Se ha presentado un problema tratando de realizar un update de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00026.getIdentificador(), new Mensaje(CodigoMensaje.M00026,
				"Se ha presentado un problema INESPERADO tratando de realizar un update de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00027.getIdentificador(), new Mensaje(CodigoMensaje.M00027,
				"Se ha presentado un problema tratando de realizar un delete de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00028.getIdentificador(), new Mensaje(CodigoMensaje.M00028,
				"Se ha presentado un problema INESPERADO tratando de realizar un delete de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Postgre SQL..."));
		mensajes.put(CodigoMensaje.M00029.getIdentificador(), new Mensaje(CodigoMensaje.M00029,
				"Se ha presentado un problema ejecutando la sentancia SQL de consulta de las unidades deportivas en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00030.getIdentificador(), new Mensaje(CodigoMensaje.M00030,
				"Se ha presentado un problema INESPERADO ejecutando la sentancia SQL de consulta de las unidades deportivas en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00035.getIdentificador(), new Mensaje(CodigoMensaje.M00035,
				"Se ha presentado un problema ejecutando la sentancia SQL de consulta de los deportes en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00037.getIdentificador(), new Mensaje(CodigoMensaje.M00037,
				"No es posible crear el DAO deseado dado que la conexion SQL esta cerrada"));
		mensajes.put(CodigoMensaje.M00038.getIdentificador(), new Mensaje(CodigoMensaje.M00038,
				"No ha sido posible consultar la información de los deportes. Por favor, inténtelo de nuevo o comuníquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00039.getIdentificador(), new Mensaje(CodigoMensaje.M00039,
				"No ha sido posible llevar a cabo la consulta de la informacion de los deportes. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
	}

	@Override
	public final String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(CodigoMensaje codigo, final String... parametros) {

		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		if (codigo.isBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005,
					codigo.getIdentificador());
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006,
					codigo.getIdentificador());
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		// TODO: Tarea: asegure que si tiene parámetros, el contenido
		// del mensaje se retorne con los parámetros reemplazados
		// {1}, {2}, {3}

		return mensajes.get(codigo.getIdentificador());
	}

}