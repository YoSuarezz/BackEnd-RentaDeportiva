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
		mensajes.put(CodigoMensaje.M00017.getIdentificador(), new Mensaje(CodigoMensaje.M00017,
				"Se ha presentado un problema tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
		mensajes.put(CodigoMensaje.M00018.getIdentificador(), new Mensaje(CodigoMensaje.M00018,
				"Se ha presentado un problema INESPERADO tratando de confirmar una transaccion SQL con la fuente de informacion deseada..."));
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
				"El deporte debe contener unicamente letras y espacios, intente por favor nuevamente con un nombre valido "));
		mensajes.put(CodigoMensaje.M00042.getIdentificador(), new Mensaje(CodigoMensaje.M00042,
				"El nombre del Deporte no puede exceder los 20 caracteres, por favor intente nuevamente con un deporte valido "));
		mensajes.put(CodigoMensaje.M00043.getIdentificador(), new Mensaje(CodigoMensaje.M00043,
				"No existe configurada una factoria de datos para una base de datos SQL SERVER"));
		mensajes.put(CodigoMensaje.M00044.getIdentificador(), new Mensaje(CodigoMensaje.M00044,
				"Se ha presentado un problema tratando de obtener la conexión con la base de datos..."));
		mensajes.put(CodigoMensaje.M00045.getIdentificador(), new Mensaje(CodigoMensaje.M00045,
				"Se ha presentado un problema INESPERADO tratando de obtener la conexión con la base de datos..."));
		mensajes.put(CodigoMensaje.M00046.getIdentificador(), new Mensaje(CodigoMensaje.M00046,
				"Se ha presentado un problema tratando de consultar la informacion de los tipos de espacios deportivos"));
		mensajes.put(CodigoMensaje.M00047.getIdentificador(), new Mensaje(CodigoMensaje.M00047,
				"Se ha presentado un problema inesperado tratando de consultar la informacion de los tipos de espacios deportivos en el metodo ejecutar de la clase ConsultarTipoEspacioDeportivoFachadaImpl. Por favor revise la traza completa del problema"));
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
		mensajes.put(CodigoMensaje.M00056.getIdentificador(), new Mensaje(CodigoMensaje.M00056,
				"Se registro correctamente el tipo de espacio deportivo"));
		mensajes.put(CodigoMensaje.M00057.getIdentificador(), new Mensaje(CodigoMensaje.M00057,
				"Tipos de espacios deportivos consultados exitosamente"));
		mensajes.put(CodigoMensaje.M00058.getIdentificador(), new Mensaje(CodigoMensaje.M00058,
				"Se ha presentado un problema tratando de registrar el tipo de espacio deportivo"));
		mensajes.put(CodigoMensaje.M00059.getIdentificador(), new Mensaje(CodigoMensaje.M00059,
				"Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo tipo de espacio deportivo en el metodo ejecutar de la clase RegistrarTipoEspacioDeportivoFachadaImpl. Por favor revise la traza completa del problema"));
		mensajes.put(CodigoMensaje.M00060.getIdentificador(), new Mensaje(CodigoMensaje.M00060,
				"Se ha presentado un problema consultando los datos de Unidad Deportiva en la base de datos."));
		mensajes.put(CodigoMensaje.M00061.getIdentificador(), new Mensaje(CodigoMensaje.M00061,
				"Se ha presentado un problema INESPERADO consultando los datos de Unidad Deportiva en la base de datos."));
		mensajes.put(CodigoMensaje.M00062.getIdentificador(), new Mensaje(CodigoMensaje.M00062,
				"Se ha presentado un problema actualizando los datos de Unidad Deportiva en la base de datos."));
		mensajes.put(CodigoMensaje.M00068.getIdentificador(), new Mensaje(CodigoMensaje.M00068,
				"La fecha proporcioanda esta vacia o es nula"));
		mensajes.put(CodigoMensaje.M00069.getIdentificador(), new Mensaje(CodigoMensaje.M00069,
				"La fecha de inicio no puede ser posterior a la fecha de fin."));
		mensajes.put(CodigoMensaje.M00070.getIdentificador(), new Mensaje(CodigoMensaje.M00070,
				"Ya existe un tipo de espacio deportivo con el mismo nombre."));
		mensajes.put(CodigoMensaje.M00071.getIdentificador(), new Mensaje(CodigoMensaje.M00071,
				"El nombre del tipo de espacio deportivo es obligatorio, por favor ingrese un nombre valido"));
		mensajes.put(CodigoMensaje.M00072.getIdentificador(), new Mensaje(CodigoMensaje.M00072,
				"El nombre del tipo de espacio no puede exceder los 20 caracteres, por favor intente nuevamente con un nombre valido"));
		mensajes.put(CodigoMensaje.M00073.getIdentificador(), new Mensaje(CodigoMensaje.M00073,
				"El nombre del tipo de espacio no puede tener letras y espacios, por favor intente nuevamente con un nombre valido"));
		mensajes.put(CodigoMensaje.M00074.getIdentificador(), new Mensaje(CodigoMensaje.M00074,
				"La cantidad de espacios debe ser un numero que este entre 1 y 49"));
		mensajes.put(CodigoMensaje.M00075.getIdentificador(), new Mensaje(CodigoMensaje.M00075,
				"No existe el tipo de espacio deportivo con el ID seleccionado"));
		mensajes.put(CodigoMensaje.M00076.getIdentificador(), new Mensaje(CodigoMensaje.M00076,
				"El ID del tipo de espacio deportivo es obligatorio"));
		mensajes.put(CodigoMensaje.M00077.getIdentificador(), new Mensaje(CodigoMensaje.M00077,
				"El ID del tipo de espacio deportivo debe ser un valor positivo mayor que cero."));
		mensajes.put(CodigoMensaje.M00078.getIdentificador(), new Mensaje(CodigoMensaje.M00078,
				"No existe el tipo de espacio deportivo que se desea editar, por favor intente con uno nuevo que si exista en la base de datos"));
		mensajes.put(CodigoMensaje.M00079.getIdentificador(), new Mensaje(CodigoMensaje.M00079,
				"No es posible establecer el nombre debido a que ya hay otro tipo de espacio deportivo con el mismo nombre"));
		mensajes.put(CodigoMensaje.M00080.getIdentificador(), new Mensaje(CodigoMensaje.M00080,
				"Por favor complete todos los campos"));
		mensajes.put(CodigoMensaje.M00081.getIdentificador(), new Mensaje(CodigoMensaje.M00081,
				"El nombre del tipo de espacio deportivo no puede exceder los 20 caracteres, por favor intente nuevamente con un nombre valido"));
		mensajes.put(CodigoMensaje.M00082.getIdentificador(), new Mensaje(CodigoMensaje.M00082,
				"El nombre del tipo de espacio solo puede tener letras y espacios, por favor intente nuevamente con un nombre valido"));
		mensajes.put(CodigoMensaje.M00083.getIdentificador(), new Mensaje(CodigoMensaje.M00083,
				"La cantidad de espacios debe ser un numero que este entre 1 y 49, y el campo no puede estar vacío "));
		mensajes.put(CodigoMensaje.M00084.getIdentificador(), new Mensaje(CodigoMensaje.M00084,
				"Ya existe una tarifa estándar para este tipo de espacio deportivo."));
		mensajes.put(CodigoMensaje.M00085.getIdentificador(), new Mensaje(CodigoMensaje.M00085,
				"El ID debe ser positivo."));
		mensajes.put(CodigoMensaje.M00086.getIdentificador(), new Mensaje(CodigoMensaje.M00086,
				"El precio por hora debe ser positivo."));
		mensajes.put(CodigoMensaje.M00087.getIdentificador(), new Mensaje(CodigoMensaje.M00087,
				"El nombre no puede ser nulo o vacío."));
		mensajes.put(CodigoMensaje.M00088.getIdentificador(), new Mensaje(CodigoMensaje.M00088,
				"La fecha y hora de inicio no pueden ser nulas o vacias."));
		mensajes.put(CodigoMensaje.M00089.getIdentificador(), new Mensaje(CodigoMensaje.M00089,
				"La fecha y hora de fin no pueden ser nulas o vacias."));
		mensajes.put(CodigoMensaje.M00090.getIdentificador(), new Mensaje(CodigoMensaje.M00090,
				"La fecha de inicio no puede ser posterior a la fecha de fin."));
		mensajes.put(CodigoMensaje.M00091.getIdentificador(), new Mensaje(CodigoMensaje.M00091,
				"Se ha presentado un problema tratando de editar el tipo de espacio deportivo."));
		mensajes.put(CodigoMensaje.M00092.getIdentificador(), new Mensaje(CodigoMensaje.M00092,
				"Se ha presentado un problema inesperado tratando de editar la información del tipo de espacio deportivo en el método ejecutar de la clase ActualizarTipoEspacioDeportivoFachadaImpl. Por favor revise la traza completa del problema."));
		mensajes.put(CodigoMensaje.M00093.getIdentificador(), new Mensaje(CodigoMensaje.M00093,
				"Se ha presentado un problema tratando de crear la tarifa Estandar"));
		mensajes.put(CodigoMensaje.M00094.getIdentificador(), new Mensaje(CodigoMensaje.M00094,
				"Se ha presentado un problema INESPERADO tratando de crear la informacion de la nueva tarifa estandar en el metodo ejecutar de la clase CrearTarifaEstandarFachadaImpl. Por favor revise la traza completa del problema"));
		mensajes.put(CodigoMensaje.M00095.getIdentificador(), new Mensaje(CodigoMensaje.M00095,
				"La fecha fin no puede ser anterior a la fecha de inicio"));
		mensajes.put(CodigoMensaje.M00096.getIdentificador(), new Mensaje(CodigoMensaje.M00096,
				"Tipo de espacio deportivo actualizado correctamente."));
		mensajes.put(CodigoMensaje.M00097.getIdentificador(), new Mensaje(CodigoMensaje.M00097,
				"Tipo de espacio deportivo eliminado correctamente."));
		mensajes.put(CodigoMensaje.M00098.getIdentificador(), new Mensaje(CodigoMensaje.M00098,
				"Se creó correctamente la tarifa estándar para el espacio deportivo."));
		mensajes.put(CodigoMensaje.M00099.getIdentificador(), new Mensaje(CodigoMensaje.M00099,
				"Se ha presentado un problema INESPERADO tratando de ingresar los datos de la tarifa estándar a la base de datos."));
		mensajes.put(CodigoMensaje.M00100.getIdentificador(), new Mensaje(CodigoMensaje.M00100,
				"Se ha presentado un problema INESPERADO tratando de consultar las tarifas estándar en la base de datos."));
		mensajes.put(CodigoMensaje.M00101.getIdentificador(), new Mensaje(CodigoMensaje.M00101,
				"El tipo de espacio deportivo seleccionado no existe."));
		mensajes.put(CodigoMensaje.M00102.getIdentificador(), new Mensaje(CodigoMensaje.M00102,
				"se ha presentado un problema tratando de eliminar el tipo de espacio de deportivo"));
		mensajes.put(CodigoMensaje.M00103.getIdentificador(), new Mensaje(CodigoMensaje.M00103,
				"Se ha presentado un problema inesperado tratando de eliminar el tipo de espacio de deportivo"));
		mensajes.put(CodigoMensaje.M00104.getIdentificador(), new Mensaje(CodigoMensaje.M00104,
				"El nombre de la tarifa estandar no puede exceder los 35 caracteres, por favor intente nuevamente con un nombre valido"));
		mensajes.put(CodigoMensaje.M00105.getIdentificador(), new Mensaje(CodigoMensaje.M00105,
				"No existe el deporte con el cual se esperaba crear el tipo de espacio deportivo"));

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

		Mensaje mensaje = mensajes.get(codigo.getIdentificador());
		String contenido = mensaje.getContenido();

		// Reemplazar los parámetros en el contenido del mensaje
		for (int i = 0; i < parametros.length; i++) {
			contenido = contenido.replace("${" + (i + 1) + "}", parametros[i]);
		}


		return mensajes.get(codigo.getIdentificador());
	}

}
