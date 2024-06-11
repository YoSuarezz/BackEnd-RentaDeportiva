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
				"Se ha presentado un problema tratando de realizar un insert de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
		mensajes.put(CodigoMensaje.M00024.getIdentificador(), new Mensaje(CodigoMensaje.M00024,
				"Se ha presentado un problema INESPERADO tratando de realizar un insert de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
		mensajes.put(CodigoMensaje.M00025.getIdentificador(), new Mensaje(CodigoMensaje.M00025,
				"Se ha presentado un problema tratando de realizar un update de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
		mensajes.put(CodigoMensaje.M00026.getIdentificador(), new Mensaje(CodigoMensaje.M00026,
				"Se ha presentado un problema INESPERADO tratando de realizar un update de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
		mensajes.put(CodigoMensaje.M00027.getIdentificador(), new Mensaje(CodigoMensaje.M00027,
				"Se ha presentado un problema tratando de realizar un delete de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
		mensajes.put(CodigoMensaje.M00028.getIdentificador(), new Mensaje(CodigoMensaje.M00028,
				"Se ha presentado un problema INESPERADO tratando de realizar un delete de la informacion del deporte \"${1}\" en la tabla \"Deporte\" en la base de datos Azure SQL..."));
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
		mensajes.put(CodigoMensaje.M00063.getIdentificador(), new Mensaje(CodigoMensaje.M00063,
				"Se ha presnetado un problema intentando hacer la operacion deseada"));
		mensajes.put(CodigoMensaje.M00064.getIdentificador(), new Mensaje(CodigoMensaje.M00064,
				"Se ha presentado un problema INESPERADO trantando de ingresar los datos de la tarifa estandar a la base de datos"));
		mensajes.put(CodigoMensaje.M00065.getIdentificador(), new Mensaje(CodigoMensaje.M00065,
				"Se ha presentado un problema eliminando los datos de Unidad Deportiva en la base de datos."));
		mensajes.put(CodigoMensaje.M00066.getIdentificador(), new Mensaje(CodigoMensaje.M00066,
				"Se ha presentado un problema al consultar los tipos de espacio deportivo"));
		mensajes.put(CodigoMensaje.M00067.getIdentificador(), new Mensaje(CodigoMensaje.M00067,
				"Se ha presentado un problema consultando los datos de los tipos de espacios deportivos en la base de datos."));
		mensajes.put(CodigoMensaje.M00106.getIdentificador(), new Mensaje(CodigoMensaje.M00106,
				"No ha sido posible consultar la informacion de los usuarios, por favor intentelo de nuevo o comuniquese con el admin"));
		mensajes.put(CodigoMensaje.M00107.getIdentificador(), new Mensaje(CodigoMensaje.M00107,
				"Se ha presentado un problema ejecutando la sentencia SQL en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00108.getIdentificador(), new Mensaje(CodigoMensaje.M00108,
				"No ha sido posible llevar a cabo la consulta de informacion de informacion de los usuarios. Por favor intente de nuevo y en caso de persistir el problema, comuniquese conel administrador de la UD El Bernabeu"));
		mensajes.put(CodigoMensaje.M00109.getIdentificador(), new Mensaje(CodigoMensaje.M00109,
				"Se ha presentado un INESPERADO problema ejecutando la sentencia SQL de consulta en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00110.getIdentificador(), new Mensaje(CodigoMensaje.M00110,
				"No ha sido posible autenticar la informacion de los usuarios, por favor intentelo de nuevo o comuniquese con el admin"));
		mensajes.put(CodigoMensaje.M00111.getIdentificador(), new Mensaje(CodigoMensaje.M00111,
				"Se ha presentado un INESPERADO problema ejecutando la sentencia SQL de autenticacion de los usuarios en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00112.getIdentificador(), new Mensaje(CodigoMensaje.M00112,
				"No ha sido posible llevar a cabo la autenticacion de informacion de informacion de los usuarios. Por favor intente de nuevo y en caso de persistir el problema, comuniquese conel administrador de la UD El Bernabeu"));
		mensajes.put(CodigoMensaje.M00113.getIdentificador(), new Mensaje(CodigoMensaje.M00113,
				"Se ha presentado un problema INESPERADO  ejecutando la sentencia SQL de autenticacion en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00114.getIdentificador(), new Mensaje(CodigoMensaje.M00114,
				"No ha sido posible consultar la información de las monedas. Por favor, inténtelo de nuevo o comuníquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00115.getIdentificador(), new Mensaje(CodigoMensaje.M00115,
				"Se ha presentado un problema ejecutando la sentancia SQL de consulta de las monedas en la base de datos Azure SQL"));
		mensajes.put(CodigoMensaje.M00116.getIdentificador(), new Mensaje(CodigoMensaje.M00116,
				"No ha sido posible llevar a cabo la consulta de la informacion de los monedas. Por favor intente de nuevo y en caso de pérsisitir el problema, comuniquese con el administrador de la Unidad Deportiva El Bernabeu..."));
		mensajes.put(CodigoMensaje.M00117.getIdentificador(), new Mensaje(CodigoMensaje.M00117,
				"Se ha presentado un problema INESPERADO ejecutando la sentancia SQL de consulta de las monedas en la base de datos Azure SQL"));

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

		Mensaje mensaje = mensajes.get(codigo.getIdentificador());
		String contenido = mensaje.getContenido();

		// Reemplazar los parámetros en el contenido del mensaje
		for (int i = 0; i < parametros.length; i++) {
			contenido = contenido.replace("${" + (i + 1) + "}", parametros[i]);
		}

		return new Mensaje(codigo, contenido);
	}
}