package org.elb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController("consumer/mgmt")
public class ConsumerController {


    @GetMapping
    public Map<String, Object> getInfo(HttpServletRequest request) {
        final Map<String, Object> response = new HashMap<>();
        final Map<String, String> cookies = new HashMap<>();


        response.put("server", "consumer");
        response.put("cookies", cookies);

         Arrays.stream(request.getCookies())
                .forEach(cookie -> cookies.put(cookie.getName(), cookie.getValue()));

        return response;
    }

}
