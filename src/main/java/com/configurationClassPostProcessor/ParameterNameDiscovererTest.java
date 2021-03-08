package com.configurationClassPostProcessor;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

/**
 * @author 朱伟伟
 * @date 2021-02-09 15:00:00
 * @description
 */
public class ParameterNameDiscovererTest {

    private String name;


    public ParameterNameDiscovererTest(String aName) {
        this.name = aName;
    }

    public static void main(String[] args) throws Exception {
        StandardReflectionParameterNameDiscoverer parameterNameDiscoverer = new StandardReflectionParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(ParameterNameDiscovererTest.class.getConstructor(String.class));
        System.out.println(parameterNames);

        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(ParameterNameDiscovererTest.class.getConstructor(String.class));
        System.out.println(parameterNames);

        DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        parameterNames = defaultParameterNameDiscoverer.getParameterNames(ParameterNameDiscovererTest.class.getConstructor(String.class));
        System.out.println(parameterNames);
    }
}
