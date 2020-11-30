package com.example.demo;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 朱伟伟
 * @date 2020-08-28 16:46:34
 * @description
 */
@Component
//@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class RequestComponent {
}
