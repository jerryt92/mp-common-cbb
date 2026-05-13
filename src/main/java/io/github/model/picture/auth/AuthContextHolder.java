package io.github.model.picture.auth;

/**
 * 请求线程内的认证上下文持有者
 */
public final class AuthContextHolder {

    private static final ThreadLocal<AuthContext> CONTEXT = new ThreadLocal<>();

    private AuthContextHolder() {
    }

    public static void set(AuthContext authContext) {
        CONTEXT.set(authContext);
    }

    public static AuthContext get() {
        return CONTEXT.get();
    }

    public static String getUserId() {
        AuthContext authContext = CONTEXT.get();
        return authContext == null ? null : authContext.getUserId();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
