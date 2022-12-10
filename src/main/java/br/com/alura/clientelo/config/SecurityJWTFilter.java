package br.com.alura.clientelo.config;

import br.com.alura.clientelo.modal.Usuario;
import br.com.alura.clientelo.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityJWTFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;
    private final String secret;

    public SecurityJWTFilter(UsuarioRepository usuarioRepository, @Value("${jwt.secret}") String secret) {
        this.usuarioRepository = usuarioRepository;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String HEADER = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (HEADER == null || HEADER.isEmpty() || !HEADER.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String JWT_TOKEN = getSubjectPeloHeader(HEADER);
        if (isTokenValid(JWT_TOKEN)) {
            autorizar(JWT_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    private void autorizar(final String JWT_TOKEN) {
        Optional<Usuario> userDetails = usuarioRepository.findByUsername(JWT_TOKEN);
        if (userDetails.isPresent()) {
            Usuario usuario = userDetails.get();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String getSubjectPeloHeader(String HEADER) {
        return HEADER.split(" ")[1].trim();
    }

    private boolean isTokenValid(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
