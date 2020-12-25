package com.example.validation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-08-04 20:30:52
 * @description validator测试
 **/
@RestController
public class UserController {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    BeanFactory beanFactory;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    ServletRequest servletRequest;
    @Autowired
    ServletResponse servletResponse;

    /**
     * @param user:
     * @param bindingResult:
     * @author: 朱伟伟
     * @date: 2020-08-04 20:46
     * @description: param校验测试-------BindingResult
     **/
    @GetMapping("/validateParamBindingResult")
    public String validate(@Validated User user, BindingResult bindingResult) {
//    public String getUser(@Valid User user, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            allErrors.forEach(a -> System.out.println(a.getDefaultMessage()));
            return allErrors.get(0).getDefaultMessage();
        }
        return "success";
    }

    /**
     * @param user:
     * @author: 朱伟伟
     * @date: 2020-08-04 20:46
     * @description: param校验测试-------validator+自动抛出异常(BindException)
     **/
    @GetMapping("/validateParam")
    public String validate1(@Validated User user) {
        return "success";
    }


    /**
     * @param user:
     * @param bindingResult:
     * @author: 朱伟伟
     * @date: 2020-08-04 21:04
     * @description: body校验测试-------BindingResult
     **/
    @PostMapping("/validateBodyBindingResult")
    public String validateBody(@Validated @RequestBody User user, BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            allErrors.forEach(a -> System.out.println(a.getDefaultMessage()));
            return allErrors.get(0).getDefaultMessage();
        }
        return "success";
    }


    /**
     * @param user:
     * @author: 朱伟伟
     * @date: 2020-08-04 21:04
     * @description: body校验测试-------validator+自动抛出异常(MethodArgumentNotValidException)
     **/
    @PostMapping("/validateBody")
    public String validateBody(@Validated @RequestBody User user) {
        return "success";
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ServletResponse getServletResponse() {
        return servletResponse;
    }

    public void setServletResponse(ServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

}
