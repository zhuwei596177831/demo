package com.example.validation;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.validation.ConstraintViolationException;

/**
 * @author 朱伟伟
 * @date 2020-08-04 20:46
 * @description
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice {

//    @ExceptionHandler(BindException.class)
//    public String MethodArgumentNotValidExceptionHandler(BindException e) {
//        // 从异常对象中拿到ObjectError对象
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        // 然后提取错误提示信息进行返回
//        return objectError.getDefaultMessage();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        return objectError.getDefaultMessage();
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        return new Result<>(ResultCode.VALIDATE_FAILED, e.getMessage(), null);
    }

    /**
     * @param e:
     * @author: 朱伟伟
     * @date: 2021-01-14 18:21
     * @description:
     * @see org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor#resolveArgument
     **/
    @ExceptionHandler(BindException.class)
    public Result MethodArgumentNotValidExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new Result<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage(), null);
    }

    /**
     * @param e:
     * @author: 朱伟伟
     * @date: 2021-01-14 18:19
     * @description:
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#resolveArgument
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new Result<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage(), null);
    }

}
