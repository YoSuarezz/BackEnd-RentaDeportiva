package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.impl;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom.CrosscuttingUDElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.MessageCatalog;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.messagecatalog.data.Mensaje;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

public final class MessageCatalogBase implements MessageCatalog {

	private final Map<String, Mensaje> mensajes = new HashMap<>();

	@Override
	public final void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00001.getIdentificador(), new Mensaje(CodigoMensaje.M00001,
				"El código del mensaje que quiere obtener del catálogo mensajes llegó nulo..."));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(), new Mensaje(CodigoMensaje.M00002,
				"Se ha presentado un problema tratando de llevar a cabo la operación deseada..."));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(), new Mensaje(CodigoMensaje.M00003,
				"El identificador del mensaje \"${1}\" que se intentó obtener, no está en el catálogo de mensajes base..."));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(), new Mensaje(CodigoMensaje.M00004,
				"El mensaje con identificador \"${1}\" que se intentó obtener, no está configurado para residir en el catálogo de mensajes base..."));
		mensajes.put(CodigoMensaje.M00005.getIdentificador(), new Mensaje(CodigoMensaje.M00005,
				"El mensaje con identificador \"${1}\" que se intentó obtener, no está configurado para residir en el catálogo de mensajes externo..."));
		mensajes.put(CodigoMensaje.M00006.getIdentificador(), new Mensaje(CodigoMensaje.M00006,
				"El identificador del mensaje \"${1}\" que se intentó obtener, no está en el catálogo de mensajes externo..."));

		mensajes.put(CodigoMensaje.M00007.getIdentificador(), new Mensaje(CodigoMensaje.M00007,
				"Se ha presentado un problrma tratando de validar si la conexion SQL con la fuente de informacion deseada estaba cerrada..."));
		mensajes.put(CodigoMensaje.M00008.getIdentificador(), new Mensaje(CodigoMensaje.M00008,
				"Se ha presentado un probelma INESPERADO tratando de validar si la conexion SQL con la fuente de informacion deseada estaba cerrada..."));
		mensajes.put(CodigoMensaje.M00009.getIdentificador(), new Mensaje(CodigoMensaje.M00009,
				"Se ha intentado realizar el cierre de una conexion SQL que ya estaba cerrada..."));
		mensajes.put(CodigoMensaje.M00010.getIdentificador(), new Mensaje(CodigoMensaje.M00010,
				"Se ha presentado un problema intentando cerrar la conexion SQL con la fuente de informacion deseada deseada..."));
		mensajes.put(CodigoMensaje.M00011.getIdentificador(), new Mensaje(CodigoMensaje.M00011,
				"Se ha presentado un problema INESPERADO intentando cerrar la conexion SQL con la fuente de informacion deseada deseada..."));
		mensajes.put(CodigoMensaje.M00012.getIdentificador(), new Mensaje(CodigoMensaje.M00012,
				"Se ha intentado confirmar una transaccion con una conexion SQL cerrada..."));
		mensajes.put(CodigoMensaje.M00013.getIdentificador(), new Mensaje(CodigoMensaje.M00013,
				"Se ha intentado confirmar una transaccion cuando el autocommit de la conexion con la base de datos estaba activado..."));
		mensajes.put(CodigoMensaje.M00014.getIdentificador(), new Mensaje(CodigoMensaje.M00014,
				"Se ha presentado un problema tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00015.getIdentificador(), new Mensaje(CodigoMensaje.M00015,
				"Se ha presentado un problema INESPERADO tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00016.getIdentificador(), new Mensaje(CodigoMensaje.M00016,
				"Se ha intentado confirmar una transaccion cuando el autocommit de la conexion con la base de datos estaba activado..."));
		mensajes.put(CodigoMensaje.M00017.getIdentificador(), new Mensaje(CodigoMensaje.M00017,
				"Se ha presentado un problema tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00018.getIdentificador(), new Mensaje(CodigoMensaje.M00018,
				"Se ha presentado un problema INESPERADO tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00019.getIdentificador(), new Mensaje(CodigoMensaje.M00019,
				"Se ha intentado confirmar una transaccion cuando el autocommit de la conexion con la base de datos estaba activado..."));
		mensajes.put(CodigoMensaje.M00020.getIdentificador(), new Mensaje(CodigoMensaje.M00020,
				"Se ha presentado un problema tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00021.getIdentificador(), new Mensaje(CodigoMensaje.M00021,
				"Se ha presentado un problema INESPERADO tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00022.getIdentificador(), new Mensaje(CodigoMensaje.M00022,
				"Se ha presentado un problema tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00031.getIdentificador(), new Mensaje(CodigoMensaje.M00031,
				"No ha sido posible llevar a cabo la consulta de la informacion de la unidad deportiva. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00032.getIdentificador(), new Mensaje(CodigoMensaje.M00032,
				"No ha sido posible llevar a cabo el registro de la información del nuevo deporte. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00033.getIdentificador(), new Mensaje(CodigoMensaje.M00033,
				"No ha sido posible llevar a cabo la actualizacion de la informacion del deporte deseado. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00034.getIdentificador(), new Mensaje(CodigoMensaje.M00034,
				"No ha sido posible llevar a cabo la eliminacion de la informacion del deporte deseado. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00036.getIdentificador(), new Mensaje(CodigoMensaje.M00036,
				"No ha sido posible llevar a cabo la consulta de la informacion de los deportes. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00040.getIdentificador(), new Mensaje(CodigoMensaje.M00040,
				"No existe configurada una factoria de datos para una base de datos ORACLE"));
		mensajes.put(CodigoMensaje.M00041.getIdentificador(), new Mensaje(CodigoMensaje.M00041,
				"No existe configurada una factoria de datos para una base de datos MYSQL"));
		mensajes.put(CodigoMensaje.M00042.getIdentificador(), new Mensaje(CodigoMensaje.M00042,
				"No existe configurada una factoria de datos para una base de datos POSTGESQL"));
		mensajes.put(CodigoMensaje.M00043.getIdentificador(), new Mensaje(CodigoMensaje.M00043,
				"No existe configurada una factoria de datos para una base de datos SQL SERVER"));
		mensajes.put(CodigoMensaje.M00044.getIdentificador(), new Mensaje(CodigoMensaje.M00044,
				"Se ha presentado un problema tratando de obtener la conexión con la base de datos..."));
		mensajes.put(CodigoMensaje.M00045.getIdentificador(), new Mensaje(CodigoMensaje.M00045,
				"Se ha presentado un problema INESPERADO tratando de obtener la conexión con la base de datos..."));
		mensajes.put(CodigoMensaje.M00046.getIdentificador(), new Mensaje(CodigoMensaje.M00046,
				"Se ha presentado un problema tratando de consultar la informacion de los paises"));
		mensajes.put(CodigoMensaje.M00047.getIdentificador(), new Mensaje(CodigoMensaje.M00047,
				"Se ha presentado un problema INESPERADO tratando de consultar la informacion de los paises"));
		mensajes.put(CodigoMensaje.M00048.getIdentificador(), new Mensaje(CodigoMensaje.M00048,
				"Se ha presentado un problema consultando los datos de Unidad Deportiva en la base de datos..."));
		mensajes.put(CodigoMensaje.M00049.getIdentificador(), new Mensaje(CodigoMensaje.M00049,
				"Se ha presentado un problema tratando de consultar la informacion de las unidades deportivas"));
		mensajes.put(CodigoMensaje.M00050.getIdentificador(), new Mensaje(CodigoMensaje.M00050,
				"Se ha presentado un problema INESPERADO tratando de consultar la informacion de las unidades deportivas"));
		mensajes.put(CodigoMensaje.M00051.getIdentificador(), new Mensaje(CodigoMensaje.M00051,
				"Se ha presentado un problema tratando de consultar la informacion de los deportes"));
		mensajes.put(CodigoMensaje.M00052.getIdentificador(), new Mensaje(CodigoMensaje.M00052,
				"Se ha presentado un problema INESPERADO tratando de consultar la informacion de los deportes"));
		mensajes.put(CodigoMensaje.M00053.getIdentificador(), new Mensaje(CodigoMensaje.M00053,
				"Unidades deportivas consultadas exitosamente"));
		mensajes.put(CodigoMensaje.M00054.getIdentificador(), new Mensaje(CodigoMensaje.M00054,
				"Se ha presentado un problema inesperado"));
		mensajes.put(CodigoMensaje.M00055.getIdentificador(), new Mensaje(CodigoMensaje.M00055,
				"Deportes consultados exitosamente"));

	}


	@Override
	public final String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, final String... parametros) {

		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		if (!codigo.isBase()) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004, codigo.getIdentificador());
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00003, codigo.getIdentificador());
			throw new CrosscuttingUDElBernabeuException(mensajeTecnico, mensajeUsuario);
		}

		// TODO: Tarea: asegure que si tiene parámetros, el contenido
		// del mensaje se retorne con los parámetros reemplazados
		// {1}, {2}, {3}

		return mensajes.get(codigo.getIdentificador());
	}

}
