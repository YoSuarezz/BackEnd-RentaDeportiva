package co.edu.uco.rentadeportiva.crosscutting.helpers;

public final class ObjectHelper {
    private static final ObjectHelper instance = new ObjectHelper();
    private ObjectHelper() {
        super();
    }

    public static ObjectHelper getObjectHelper() {
        return instance;
    }

    public final <T> boolean isNull(final T object) {
        return object == null;
    }

    public final <T> T getDefault(final T object, final T defaulObject) {
        return isNull(object) ? defaulObject : object;
    }
}
