package org.amv.access.sdk.spi.error;

/**
 * A wrapper class for all exceptions thrown inside the sdk.
 * Most of the time this class will just wrap other exceptions
 * which can be inspected by calling {@link #getCause()}.
 */
public class AccessSdkException extends RuntimeException {
    private static final Type GENERIC = new GenericType();

    public static AccessSdkException wrap(Throwable t) {
        boolean isAccessSdkException = AccessSdkException.class
                .isAssignableFrom(t.getClass());

        return isAccessSdkException ? (AccessSdkException) t :
                new AccessSdkException(t);
    }

    private final Type type;

    private AccessSdkException(Throwable error) {
        this(GENERIC, error);
    }

    public AccessSdkException(Type type, Throwable error) {
        super(error);

        if (type == null) {
            throw new IllegalArgumentException("`type` must not be null");
        }
        if (error == null) {
            throw new IllegalArgumentException("`error` must not be null");
        }

        this.type = type;
    }

    @Override
    public String getMessage() {
        Throwable cause = this.getCause();
        return cause != null ? cause.getMessage() : "";
    }

    public Type getType() {
        return type;
    }

    public interface Type {
        String getName();

        String getDescription();
    }

    public static class GenericType implements Type {
        @Override
        public String getName() {
            return "GENERIC";
        }

        @Override
        public String getDescription() {
            return "Wrapper type for downstream exceptions";
        }
    }
}
