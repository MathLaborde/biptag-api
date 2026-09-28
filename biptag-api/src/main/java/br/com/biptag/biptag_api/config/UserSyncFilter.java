package br.com.biptag.biptag_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserSyncFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(UserSyncFilter.class);

    private final JdbcTemplate jdbcTemplate;

    private final Set<String> usuariosSincronizados = ConcurrentHashMap.newKeySet();

    public UserSyncFilter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String userId = jwt.getSubject();

            if (userId != null && !usuariosSincronizados.contains(userId)) {
                try {
                    garantirUsuario(userId, jwt);
                    usuariosSincronizados.add(userId);
                } catch (Exception e) {
                    log.error("Falha ao sincronizar usuario {} em app_users", userId, e);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void garantirUsuario(String userId, Jwt jwt) {
        Integer existe = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM app_users WHERE id = ?", Integer.class, userId);

        if (existe != null && existe > 0) {
            return;
        }

        String email = jwt.getClaimAsString("email");
        String nome = extrairNome(jwt);

        jdbcTemplate.update(
                "INSERT INTO app_users (id, email, full_name) VALUES (?, ?, ?)",
                userId, email, nome);

        log.info("Usuario {} ({}) criado em app_users", userId, email);
    }

    private String extrairNome(Jwt jwt) {
        Map<String, Object> meta = jwt.getClaimAsMap("user_metadata");
        if (meta == null) {
            return null;
        }
        Object nome = meta.get("name");
        if (nome == null) {
            nome = meta.get("full_name");
        }
        return nome != null ? nome.toString() : null;
    }
}