package io.github.model.picture.auth;

/**
 * JWT 解析后的载荷
 */
public class JwtAuthPayload {

    private final String userId;

    private final String username;

    private final String nickName;

    private final Integer role;

    public JwtAuthPayload(String userId, String username, String nickName, Integer role) {
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

    public AuthContext toAuthContext() {
        return new AuthContext(userId, username, nickName, role);
    }
}
