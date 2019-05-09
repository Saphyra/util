package com.github.saphyra.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CookieUtil {

    /**
     * Returns the cookie value with the given name.
     *
     * @param request request context
     * @param name    name of the cookie
     * @return Optional of value, or empty if cookie not found.
     */
    public Optional<String> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookieArray = request.getCookies();
        if (cookieArray == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookieArray)
            .filter(c -> c.getName().equals(name))
            .findAny()
            .map(Cookie::getValue);
    }

    /**
     * Creates a new cookie with default expiration.
     *
     * @param response response context
     * @param name     name of the cookie
     * @param value    value of the cookie
     */
    public void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, -1);
    }

    /**
     * Creates a new cookie.
     *
     * @param response   response context
     * @param name       name of the cookie
     * @param value      value of the cookie
     * @param expiration expiration of cookie.
     */
    public void setCookie(HttpServletResponse response, String name, String value, int expiration) {
        response.addCookie(createCookie(name, value, expiration));
    }

    private Cookie createCookie(String name, String value, int expiration) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(expiration);
        return cookie;
    }
}
