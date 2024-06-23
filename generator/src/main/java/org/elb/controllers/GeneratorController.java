package org.elb.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController("generator/mgmt")
public class GeneratorController {


    @GetMapping
    public Map<String, Object> getInfo(HttpServletRequest request) {
        final Map<String, Object> response = new HashMap<>();
        final Map<String, String> cookies = new HashMap<>();

        response.put("server", "generator");
        response.put("cookies", cookies);

         Arrays.stream(request.getCookies())
                .filter(c -> "myCookie".equals(c.getName()) || "nonSecureCookie".equals(c.getValue()))
                .forEach(cookie -> cookies.put(cookie.getName(), cookie.getValue()));

        return response;
    }

    @PostMapping("/set-cookie")
    public void setCookie(@RequestParam("value") final String value, HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "Secure - " + value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        Cookie newCookie = new Cookie("nonSecureCookie", "Non secure -  " + value);
        newCookie.setPath("/");
        newCookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

}