package com.example.novapo_practice05.filter;


import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


public class RequestValidationBeforeFilter  implements Filter {

    public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private Charset credentialsCharset = StandardCharsets.UTF_8;

    private final Logger LOG =
        Logger.getLogger(RequestValidationBeforeFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String header = req.getHeader(AUTHORIZATION);
        LOG.warning(header);
//        if (header != null) {
//            header = header.trim();
//            if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
//                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
//                byte[] decoded;
//                try {
//                    decoded = Base64.getDecoder().decode(base64Token);
//                    String token = new String(decoded, credentialsCharset);
//                    int delim = token.indexOf(":");
//                    if (delim == -1) {
//                        throw new BadCredentialsException("Invalid basic authentication token");
//                    }
//                    String email = token.substring(0, delim);
//                    if (email.toLowerCase().contains("test")) {
//                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                        return;
//                    }
//                } catch (IllegalArgumentException e) {
//                    throw new BadCredentialsException("Failed to decode basic authentication token");
//                }
//            }
//        }
        chain.doFilter(request, response);
    }
}
