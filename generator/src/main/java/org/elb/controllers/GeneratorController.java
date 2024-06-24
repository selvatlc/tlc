package org.elb.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("generator/mgmt")
public class GeneratorController {


    @GetMapping
    public Map<String, Object> getInfo(final HttpServletRequest request) {
        final Map<String, Object> response = new HashMap<>();
        final Map<String, String> cookies = new HashMap<>();

        response.put("server", "generator");
        response.put("cookies", cookies);

        final Cookie[] requestCookies = request.getCookies();

        if (null != requestCookies && requestCookies.length > 0) {
            Arrays.stream(requestCookies)
                    .forEach(cookie -> cookies.put(cookie.getName(), cookie.getValue()));
        }

        return response;
    }

    @PostMapping("/set-cookie")
    public Map<String, String> setCookie(@RequestParam("value") final String value, final HttpServletResponse response) {
        final Cookie cookie = new Cookie("myCookie", "Secure - " + value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        final Cookie newCookie = new Cookie("nonSecureCookie", "Non secure -  " + value);
        newCookie.setPath("/");
        newCookie.setHttpOnly(true);
        response.addCookie(newCookie);

        final Map<String, String> result = new HashMap<>();

        result.put("status", "success");

        return result;
    }

}
