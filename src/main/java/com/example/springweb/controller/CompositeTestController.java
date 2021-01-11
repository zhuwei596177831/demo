package com.example.springweb.controller;

import com.example.generic.Result;
import com.example.springweb.propertyeditor.MapPropertyEditor;
import com.example.springweb.support.MethodDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-06 17:01:06
 * @description 此方法用于初始化@ControllerAdvice标注的Bean，
 * 并解析此Bean内部各部分（@ModelAttribute、@InitBinder、RequestBodyAdvice和ResponseBodyAdvice接口）然后缓存起来。
 * {@link RequestMappingHandlerAdapter#initControllerAdviceCache}
 * {@link RequestMappingHandlerAdapter#getDataBinderFactory}
 */
@RestController
@SessionAttributes(names = {"sessionMap"}, types = {Map.class})
public class CompositeTestController {

    @Autowired
    HttpServletRequest httpServletRequest;


    /**
     * @param email:
     * @param length:
     * @param address:
     * @author: 朱伟伟
     * @date: 2021-01-11 14:36
     * @description: {@link org.springframework.web.method.annotation.RequestParamMethodArgumentResolver}
     * 标注@RequestParam并且有name属性的map: 需要自定义
     * {@link Converter} or {@link java.beans.PropertyEditor}转换
     * @see com.example.springweb.SpringWebConfig#addFormatters
     * @see com.example.springweb.controlleradvice.SpringWebExceptionAdvice#testInitBinder
     * @see #controllerInitBinder
     **/
    @MethodDesc(value = "RequestParamMethodArgumentResolver")
    @PostMapping("/requestParamMethodArgumentResolver")
    public Result requestParamMethodArgumentResolver(@RequestParam(required = false, name = "${requestParam.email}") String email,
                                                     @RequestParam(required = false, name = "#{1+5}") Integer length,
                                                     String address,
                                                     MultipartFile singleFile,
                                                     MultipartFile[] multipartFileArray,
                                                     Collection<MultipartFile> multipartFileCollection,
                                                     @RequestParam(name = "hasRequestParamNameMap") Map map
    ) {
        return Result.ok();
    }


    /**
     * @author: 朱伟伟
     * @date: 2021-01-11 15:50
     * @description: {@link org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver}
     * (requestParam != null && Map.class.isAssignableFrom(parameter.getParameterType()) &&
     * !StringUtils.hasText(requestParam.name()));
     **/
    @MethodDesc(value = "RequestParamMapMethodArgumentResolver")
    @PostMapping("/requestParamMapMethodArgumentResolver")
    public Result requestParamMapMethodArgumentResolver(
//            @RequestParam Map map,
            @RequestParam MultiValueMap multiValueMap,
            MultipartFile singleFile
    ) {
        return Result.ok();
    }


    /**
     * @author: 朱伟伟
     * @date: 2021-01-06 16:54
     * @description: 先执行@ControllerAdvice ModelAttribute然后controller的ModelAttribute
     * (! AnnotatedElementUtils.hasAnnotation ( method, RequestMapping.class) &&
     * AnnotatedElementUtils.hasAnnotation(method, ModelAttribute.class));
     **/
//    @ModelAttribute(name = "name")
//    public String controllerAdviceModelAttribute(ModelMap modelMap, NativeWebRequest nativeWebRequest) {
//        return "zww";
//    }

//    @ModelAttribute(name = "name")
//    public void controllerAdviceModelAttribute(ModelMap modelMap, NativeWebRequest nativeWebRequest) {
//        modelMap.put("name", "zww");
//    }
//    @ModelAttribute
//    public void controllerAdviceModelAttribute(String name) {
//        System.out.println("");
//    }

    /**
     * @param webDataBinder:
     * @param name:
     * @author: 朱伟伟
     * @date: 2021-01-06 16:56
     * @description: AnnotatedElementUtils.hasAnnotation(method, InitBinder.class);
     * @InitBinder 所在方法必须是void
     * 进入HandlerMethod、@ModelAttribute标注的方法前执行每一个InitBinder进行满足条件参数的数据自定义绑定
     * 先执行@ControllerAdvice initBinder然后controller的initBinder
     * 方法参数类型
     * @see RequestMappingHandlerAdapter#getInitBinderArgumentResolvers
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter
     * 属性value: HandlerMethod方法参数
     * @see org.springframework.web.method.annotation.InitBinderDataBinderFactory#isBinderMethodApplicable
     **/
    @InitBinder(value = {"hasRequestParamNameMap", "address"})
    public void controllerInitBinder(WebDataBinder webDataBinder, NativeWebRequest webRequest) {
        System.out.println(webDataBinder.getClass());
        webDataBinder.registerCustomEditor(Map.class, new MapPropertyEditor());
    }

    private static class RequestParamEntity {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}