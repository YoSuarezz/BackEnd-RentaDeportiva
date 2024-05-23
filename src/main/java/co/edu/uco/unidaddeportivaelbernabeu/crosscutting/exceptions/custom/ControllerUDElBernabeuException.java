package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.custom;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.UnidadDeportivaElBernabeuException;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.exceptions.enums.Lugar;

public final class ControllerUDElBernabeuException extends UnidadDeportivaElBernabeuException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.CONTROLLER;

	public ControllerUDElBernabeuException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}

	public ControllerUDElBernabeuException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}

	public ControllerUDElBernabeuException(final String mensajeTecnico, final String mensajeUsuario,
										   final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
}
