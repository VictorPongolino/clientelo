package br.com.alura.clientelo.config;

import br.com.alura.clientelo.modal.Usuario;
import br.com.alura.clientelo.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final JWTProps jwtProps;

    public SecurityJWTFilter(UsuarioRepository usuarioRepository, JWTProps jwtProps) {
        this.usuarioRepository = usuarioRepository;
        this.jwtProps = jwtProps;
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String REQUEST_URL = request.getRequestURL().toString().toLowerCase();
        if (!"GET".equals(request.getMethod())) {
            return false;
        }

        return "api/produtos".equals(REQUEST_URL) || "api/categorias".equals(REQUEST_URL);
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
            Jwts.parser().setSigningKey(jwtProps.getSecret()).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
