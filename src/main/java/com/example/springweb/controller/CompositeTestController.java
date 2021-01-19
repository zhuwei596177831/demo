package com.example.springweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.generic.Result;
import com.example.springweb.entity.WorkGroup;
import com.example.springweb.propertyeditor.MapPropertyEditor;
import com.example.springweb.support.MethodDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.constraints.NotEmpty;
import java.beans.ConstructorProperties;
import java.util.*;

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
@Validated
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
                                                     @NotEmpty String address,
                                                     MultipartFile singleFile,
                                                     MultipartFile[] multipartFileArray,
                                                     Collection<MultipartFile> multipartFileCollection,
                                                     @RequestParam(name = "hasRequestParamNameMap") Map map
    ) {
        return Result.ok();
    }

    @PostMapping("/testLog")
    public Result testLog(@RequestParam String id, @RequestParam String name) {
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
     * @param path:
     * @param map:
     * @author: 朱伟伟
     * @date: 2021-01-12 11:08
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver}
     * 标注@PathVariable并且有name属性的map: 需要自定义
     * {@link Converter} or {@link java.beans.PropertyEditor}转换
     **/
    @MethodDesc(value = "PathVariableMethodArgumentResolver")
    @PostMapping("/pathVariableMethodArgumentResolver/{path}/{secondPatch}/{hasNamePathMap}")
    public Result pathVariableMethodArgumentResolver(
            @PathVariable(name = "path", required = false) String path,
            @PathVariable(name = "secondPatch", required = false) String secondPatch,
            @PathVariable(name = "hasNamePathMap", required = false) Map hasNamePathMap
    ) {
        return Result.ok();
    }

    /**
     * @param notHaveNamePathMap:
     * @author: 朱伟伟
     * @date: 2021-01-12 13:36
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.PathVariableMapMethodArgumentResolver}
     * (ann != null && Map.class.isAssignableFrom(parameter.getParameterType()) && !StringUtils.hasText(ann.value()))
     **/
    @MethodDesc(value = "PathVariableMapMethodArgumentResolver")
    @PostMapping("/pathVariableMapMethodArgumentResolver/{notHaveNamePathMap}")
    public Result pathVariableMapMethodArgumentResolver(
            @PathVariable(required = false) Map notHaveNamePathMap
    ) {
        return Result.ok();
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-12 16:54
     * @description: {@link org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver}
     * (parameter.hasParameterAnnotation(RequestHeader.class)
     * && !Map.class.isAssignableFrom(parameter.nestedIfOptional().getNestedParameterType()))
     **/
    @MethodDesc(value = "RequestHeaderMethodArgumentResolver")
    @PostMapping("/requestHeaderMethodArgumentResolver")
    public Result requestHeaderMethodArgumentResolver(
            @RequestHeader(name = "key") String key,
            @RequestHeader(name = "sign") String sign
    ) {
        return Result.ok();
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-12 16:58
     * @description: {@link org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver}
     * (parameter.hasParameterAnnotation(RequestHeader.class) && Map.class.isAssignableFrom(parameter.getParameterType()))
     **/
    @MethodDesc(value = "RequestHeaderMapMethodArgumentResolver")
    @PostMapping("/requestHeaderMapMethodArgumentResolver")
    public Result requestHeaderMapMethodArgumentResolver(
            @RequestHeader(name = "map") Map map,
            @RequestHeader(name = "multiValueMap") MultiValueMap multiValueMap
    ) {
        return Result.ok();
    }

    /**
     * @param matrixVariablePath:
     * @param id:
     * @param nameList:
     * @author: 朱伟伟
     * @date: 2021-01-13 9:53
     * http://127.0.0.1:8082/demo/matrixVariableMethodArgumentResolver/qwer;id=123456;nameList=zww,myly
     * @description: 矩阵变量 {@link org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMethodArgumentResolver}
     **/
    @MethodDesc(value = "MatrixVariableMethodArgumentResolver")
    @PostMapping("/matrixVariableMethodArgumentResolver/{matrixVariablePath}")
    public Result matrixVariableMethodArgumentResolver(
            @PathVariable(name = "matrixVariablePath") String matrixVariablePath,
            @MatrixVariable(name = "id", pathVar = "matrixVariablePath") String id,
            @MatrixVariable(name = "nameList", pathVar = "matrixVariablePath") List<String> nameList
    ) {
        return Result.ok();
    }

    @ModelAttribute
    public void testModelAttribute(ModelMap modelMap) {
        ModelAttributeEntity modelAttributeEntity = new ModelAttributeEntity("111");
        modelMap.addAttribute("modelAttributeEntity", modelAttributeEntity);
        WorkGroup workGroup = new WorkGroup();
        workGroup.setName("ddd");
        workGroup.setLeaderName("ggg");
        modelMap.addAttribute("modelWorkGroup", workGroup);
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-13 18:01
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor}
     **/
    @MethodDesc(value = "ServletModelAttributeMethodProcessor")
    @PostMapping("/servletModelAttributeMethodProcessor/{pathValue}")
    public Result servletModelAttributeMethodProcessor(
            @ModelAttribute(name = "name") String name,
            @ModelAttribute(name = "pathValue") String pathValue,
            @ModelAttribute(name = "modelAttributeEntity", binding = false) ModelAttributeEntity modelAttributeEntity,
            @ModelAttribute(name = "modelWorkGroup", binding = false) WorkGroup modelWorkGroup,
            @Validated WorkGroup requestWorkGroup
    ) {
        return Result.ok();
    }

    /**
     * @param bytes:
     * @param data:
     * @author: 朱伟伟
     * @date: 2021-01-18 17:04
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor}
     * {@link org.springframework.http.converter.HttpMessageConverter}
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice}
     **/
    @MethodDesc(value = "RequestResponseBodyMethodProcessor")
    @PostMapping("/requestResponseBodyMethodProcessor")
    public String requestResponseBodyMethodProcessor(
            @RequestBody String data
//            @RequestBody WorkGroup workGroup
    ) {
        return "success";
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-19 15:28
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.RequestPartMethodArgumentResolver}
     **/
    @PostMapping("/requestPartMethodArgumentResolver")
    public String requestPartMethodArgumentResolver(
            @RequestPart MultipartFile singleFile,
            @RequestPart Collection<MultipartFile> fileCollection,
            @RequestPart MultipartFile[] fileArray,
            @RequestPart Part singlePart,
            @RequestPart Collection<Part> partCollection,
            @RequestPart Part[] partArray
    ) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "朱伟伟");
        return jsonObject.toJSONString();
    }


    /**
     * @param JSESSIONID:
     * @param cookie:
     * @author: 朱伟伟
     * @date: 2021-01-19 17:42
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.ServletCookieValueMethodArgumentResolver}
     **/
    @PostMapping("/servletCookieValueMethodArgumentResolver")
    public Map<String, String> servletCookieValueMethodArgumentResolver(
            @CookieValue(value = "JSESSIONID") String JSESSIONID,
            @CookieValue(value = "cookie") Cookie cookie
    ) {
        Map<String, String> map = new HashMap<>(4);
        map.put("JSESSIONID", JSESSIONID);
        map.put("cookie", cookie.getValue());
        return map;
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
     * 前提：参数对应的HandlerMethodArgumentResolver有调用binderFactory.createBinder方法
     * @like org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver实现
     * @like org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
     * @like org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
     * 方法参数类型
     * @see RequestMappingHandlerAdapter#getInitBinderArgumentResolvers
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter
     * 属性value: HandlerMethod方法参数
     * @see org.springframework.web.method.annotation.InitBinderDataBinderFactory#isBinderMethodApplicable
     **/
    @InitBinder(value = {"hasRequestParamNameMap", "address", "hasNamePathMap"})
    public void controllerInitBinder(WebDataBinder webDataBinder, NativeWebRequest webRequest) {
        System.out.println(webDataBinder.getClass());
        webDataBinder.registerCustomEditor(Map.class, new MapPropertyEditor());
    }

    private static class ModelAttributeEntity {
        private String id;

        public ModelAttributeEntity(String id) {
            this.id = id;
        }

        public ModelAttributeEntity() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
