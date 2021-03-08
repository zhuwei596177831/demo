package com.configurationClassPostProcessor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Inherited
@RequestMapping
public @interface TestAnnotation {
}
