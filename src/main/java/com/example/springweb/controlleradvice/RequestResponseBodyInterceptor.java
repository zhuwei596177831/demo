package com.example.springweb.controlleradvice;

import com.example.generic.Result;
import com.example.springweb.support.MethodDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author 朱伟伟
 * @date 2021-01-06 17:36:56
 * @description RequestBodyAdvice ResponseBodyAdvice 拦截@RequestBody @ResponseBody
 * 必须配合@ControllerAdvice一起使用
 */
@ControllerAdvice(annotations = {RestController.class})
public class RequestResponseBodyInterceptor extends RequestBodyAdviceAdapter implements ResponseBodyAdvice<Result> {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseBodyInterceptor.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod() != null && Result.class.isAssignableFrom(returnType.getMethod().getReturnType());
    }

    @Override
    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        MethodDesc methodDesc = returnType.getMethodAnnotation(MethodDesc.class);
        String desc = methodDesc != null ? methodDesc.value() : returnType.getContainingClass().getName() + "." + Objects.requireNonNull(returnType.getMethod()).getName();
        logger.info("{}返回:\n{}", desc, body);
        return body;
    }
}