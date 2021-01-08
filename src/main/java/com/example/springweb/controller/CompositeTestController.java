package com.example.springweb.controller;

import com.example.generic.Result;
import com.example.springweb.support.MethodDesc;
import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
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
@RestController
@SessionAttributes(names = {"sessionMap"}, types = {Map.class})
public class CompositeTestController {

    @MethodDesc(value = "RequestBodyAdvice和ResponseBodyAdvice测试")
    @GetMapping("/testRequestResponseBodyAdvice")
    public Result testGetDataBinderFactory(@RequestBody(required = false) String data,
                                           @ModelAttribute(name = "name") String name
//                                           @ModelAttribute(name = "sessionMap") Map sessionMap
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
    @ModelAttribute
    public void controllerAdviceModelAttribute(@RequestParam Map map) {
        System.out.println("");
    }

    /**
     * @param webDataBinder:
     * @param name:
     * @author: 朱伟伟
     * @date: 2021-01-06 16:56
     * @description: AnnotatedElementUtils.hasAnnotation(method, InitBinder.class);
     * @InitBinder 所在方法必须是void
     * 进入HandlerMethod、@ModelAttribute标注的方法前执行每一个InitBinder进行数据绑定
     * 先执行@ControllerAdvice initBinder然后controller的initBinder
     * 方法参数类型
     * @see RequestMappingHandlerAdapter#getInitBinderArgumentResolvers
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter
     **/
    @InitBinder
    public void controllerInitBinder(WebDataBinder webDataBinder, NativeWebRequest webRequest, String name) {
        System.out.println(webDataBinder.getClass());
    }

}
