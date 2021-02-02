package com.noxmlweb;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 朱伟伟
 * @date 2021-02-01 11:20:08
 * @description
 * @see javax.servlet.ServletContainerInitializer
 * @see org.springframework.web.SpringServletContainerInitializer
 * @see org.springframework.web.WebApplicationInitializer
 */
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
