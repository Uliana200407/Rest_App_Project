package filters;

import model.User;
import services.AuthorizationService;
import util.CustomHttpMethod;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private final AuthorizationService authorizationService;

    public AuthFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User authenticatedUser = getAuthenticatedUser(httpRequest);
        if (authenticatedUser == null) {
            httpResponse.sendRedirect("/login");
            return;
        }

        String httpMethod = httpRequest.getMethod();
        CustomHttpMethod customHttpMethod = CustomHttpMethod.valueOf(httpMethod);
        String requestUri = httpRequest.getRequestURI();
        if (!authorizationService.hasAccess(authenticatedUser, customHttpMethod, requestUri)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access forbidden");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private User getAuthenticatedUser(HttpServletRequest request) {
        return null;
    }
}
