package com.example.springweb.controlleradvice;

import com.example.generic.Result;
import com.example.springweb.entity.ResultCode;
import com.example.springweb.propertyeditor.MapPropertyEditor;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-06 17:01:06
 * @description 此方法用于初始化@ControllerAdvice标注的Bean，
 * 并解析此Bean内部各部分（@ModelAttribute、@InitBinder、RequestBodyAdvice和ResponseBodyAdvice接口）然后缓存起来。
 * {@link RequestMappingHandlerAdapter#initControllerAdviceCache}
 * {@link RequestMappingHandlerAdapter#getDataBinderFactory}
 */
@RestControllerAdvice
@Order(value = 10)
public class SpringWebExceptionAdvice {

    /**
     * @author: 朱伟伟
     * @date: 2021-01-06 16:54
     * @description: (! AnnotatedElementUtils.hasAnnotation ( method, RequestMapping.class) &&
     * AnnotatedElementUtils.hasAnnotation(method, ModelAttribute.class));
     **/
//    @ModelAttribute(name = "name")
//    public String exceptionAdviceModelAttribute(ModelMap modelMap, NativeWebRequest nativeWebRequest) {
//        return "zww";
//    }
//    @ModelAttribute
//    public void exceptionAdviceModelAttribute(String name) {
//        System.out.println("");
//    }

    /**
     * @param webDataBinder:
     * @param name:
     * @author: 朱伟伟
     * @date: 2021-01-06 16:56
     * @description: AnnotatedElementUtils.hasAnnotation(method, InitBinder.class);
     **/
//    @InitBinder
//    public void testInitBinder(WebDataBinder webDataBinder, String name) {
//        System.out.println(webDataBinder.getClass());
//        webDataBinder.registerCustomEditor(Map.class, new MapPropertyEditor());
//    }

    /**
     * @param e:
     * @author: 朱伟伟
     * @date: 2021-01-19 15:03
     * @description:
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#readWithMessageConverters
     **/
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResultCode.REQUEST_BODY_MISSING.getResult(e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public Result exception(Exception e) {
        return ResultCode.COMMON.getResult(e.getMessage());
    }


}
