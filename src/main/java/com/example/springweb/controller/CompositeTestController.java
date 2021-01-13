package com.example.springweb.controller;

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
import java.util.List;
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
     * @param path:
     * @param map:
     * @author: 朱伟伟
     * @date: 2021-01-12 11:08
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver}
     * 标注@PathVariable并且有name属性的map: 需要自定义
     * {@link Converter} or {@link java.beans.PropertyEditor}转换
     **/
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
    @PostMapping("/servletModelAttributeMethodProcessor/{pathValue}")
    public Result servletModelAttributeMethodProcessor(
            @ModelAttribute(name = "name") String name,
            @ModelAttribute(name = "pathValue") String pathValue,
            @ModelAttribute(name = "modelAttributeEntity") ModelAttributeEntity modelAttributeEntity,
            @ModelAttribute(name = "modelWorkGroup") WorkGroup modelWorkGroup,
            WorkGroup requestWorkGroup
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
     * 前提：参数对应的HandlerMethodArgumentResolver有调用binderFactory.createBinder方法
     * @like org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver实现
     * @like org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
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
