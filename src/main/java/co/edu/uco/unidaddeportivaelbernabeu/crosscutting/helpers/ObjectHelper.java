package co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers;

public final class ObjectHelper {
	private static final ObjectHelper instance = new ObjectHelper();

	public ObjectHelper() {
		super();
	}

	public static final ObjectHelper getObjectHelper() {
		return instance;
	}

	public final <T> boolean isNull(final T object) {
		return object == null;
	}

	public final <T> T getDefault(final T object, final T defaultObject) {
		return isNull(object) ? defaultObject : object;
	}

}
