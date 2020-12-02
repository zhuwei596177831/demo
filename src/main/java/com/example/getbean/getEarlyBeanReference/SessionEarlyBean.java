package com.example.getbean.getEarlyBeanReference;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.ManagedBean;

/**
 * @author 朱伟伟
 * @date 2020-12-02 17:14:05
 * @description
 */
@ManagedBean
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class SessionEarlyBean {
}
