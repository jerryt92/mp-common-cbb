package io.github.model.picture.auth;

/**
 * 当前请求认证上下文
 */
public class AuthContext {

    private final String userId;

    private final String username;

    private final String nickName;

    private final Integer role;

    public AuthContext(String userId, String username, String nickName, Integer role) {
        this.userId = userId;
        this.username = username;
        this.nickName = nickName;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getRole() {
        return role;
    }
}
