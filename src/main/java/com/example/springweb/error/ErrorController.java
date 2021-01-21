package com.example.springweb.error;


import com.example.generic.Result;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-21 16:09:54
 * @description
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final ErrorProperties errorProperties;

    public ErrorController(ServerProperties serverProperties) {
        this.errorProperties = serverProperties.getError();
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String errorHtml(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("time", Instant.now().toEpochMilli());
        return "error/" + statusCode;
    }

    @RequestMapping
    @ResponseBody
    public Map error(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("time", LocalDateTime.now());
        map.put("path", request.getRequestURL().toString());
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        map.put("status", status);
        map.put("error", HttpStatus.valueOf(status).getReasonPhrase());
        return map;
    }


    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }
}
