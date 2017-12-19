package Module.Auth;

import Module.User.TokenEntity;
import Module.User.TokenService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class ServletFilter implements Filter {
    private TokenService tokenService = new TokenService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String path = httpServletRequest.getRequestURL().toString();
        String urlEncode = URLEncoder.encode(path, "UTF-8");
        String origin = httpServletRequest.getHeader("ORIGIN");
        if (origin != null) origin = URLEncoder.encode(origin, "UTF-8");
        String[] urlSplit = httpServletRequest.getRequestURL().toString().split("/");
        String host = urlSplit[0] + "//" + urlSplit[2];
        if (path.contains("/api/authentication/") || path.contains("/Login.html") || path.contains("/Angular4/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            if (origin == null || !origin.startsWith("http://localhost:4200")) {
                httpServletResponse.sendRedirect(host + "/Login.html?redirect=" + urlEncode + "&originUrl=" + origin);
                return;
            }
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            httpServletResponse.sendRedirect(host + "/Login.html?redirect=" + urlEncode + "&originUrl=" + origin);
            return;
        }
        String tokenKey = null;
        for (Cookie cookie : cookies) {
            if ("auth-tokenKey".equals(cookie.getName())) {
                tokenKey = cookie.getValue();
            }
        }
        if (tokenKey == null || tokenKey.trim().equals("")) {
            httpServletResponse.sendRedirect(host + "/Login.html?redirect=" + urlEncode + "&originUrl=" + origin);
            return;
        }
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey);
        if (tokenEntity == null) {
            httpServletResponse.sendRedirect(host + "/Login.html?redirect=" + urlEncode + "&originUrl=" + origin);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
