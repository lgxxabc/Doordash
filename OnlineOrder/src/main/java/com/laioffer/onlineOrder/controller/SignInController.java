package com.laioffer.onlineOrder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// SecurityConfig.java 中，login fail 后要怎么做？这个类来实现
// Note: login success 的情况由 SpringSecurity 框架自动负责

@Controller
public class SignInController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    // we only process the failed login request here, if login successfully,
    // it will automatically redirect to home page
    @RequestMapping("/login")
    public void login(@RequestParam(value = "error") String error, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", "bad credentials");
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
