package token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import model.Role;
import model.User;

import java.security.Key;

public class tokenUtils {

    private static final String SECRET_KEY = "KPIPasswordGroup";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static User validateTokenAndGetUser(String token) {
        try {
            Jws < Claims > jws = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);

            Claims claims = jws.getBody();

            int userId = Integer.parseInt(claims.getSubject());
            String username = claims.get("username", String.class);
            String roleStr = claims.get("role", String.class);
            Role role = Role.valueOf(roleStr);

            return new User(userId, username, null, null, role);
        } catch (Exception e) {
            return null;
        }
    }
}
