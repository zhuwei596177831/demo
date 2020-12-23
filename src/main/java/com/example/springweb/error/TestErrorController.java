package com.example.springweb.error;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 朱伟伟
 * @date 2020-12-22 17:33:22
 * @description
 */
//@RestController
public class TestErrorController implements ErrorController {
    private static final String ERROR_ATTRIBUTE = TestErrorAttributes.class.getName() + ".ERROR";
    private final ServerProperties serverProperties;

    public TestErrorController(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    /**
     * @author: 朱伟伟
     * @date: 2020-12-23 9:21
     * @description:
     * @see org.springframework.web.context.support.WebApplicationContextUtils#registerWebApplicationScopes
     **/
//    @Autowired
    private HttpServletRequest httpServletRequest;
    private BeanFactory beanFactory;

    @Autowired
    void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Autowired
    void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @RequestMapping(value = "${server.error.path}")
    public ErrorResult errorResult() {
        HttpStatus status = getStatus(httpServletRequest);
        WebRequest webRequest = new ServletWebRequest(httpServletRequest);
        return new ErrorResult(status.value(), getErrorMessage(webRequest));
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public String getErrorMessage(WebRequest webRequest) {
        Throwable error = getAttribute(webRequest, ERROR_ATTRIBUTE);
        if (error == null) {
            error = getAttribute(webRequest, "javax.servlet.error.exception");
        }
        if (error != null) {
            return error.getClass().getName();
        }
        Object message = getAttribute(webRequest, "javax.servlet.error.message");
        if (!StringUtils.isEmpty(message)) {
            return (String) message;
        }
        return "No message available";
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }


    @Override
    public String getErrorPath() {
        return serverProperties.getError().getPath();
    }
}
