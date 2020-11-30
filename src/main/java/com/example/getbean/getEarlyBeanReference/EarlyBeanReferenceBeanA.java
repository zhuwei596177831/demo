package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.multibean.PersonService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:26
 * @description
 */
@Component
public class EarlyBeanReferenceBeanA {
    @Autowired
    Optional<EarlyBeanReferenceBeanB> referenceBeanBOptional;
    @Autowired
    ObjectFactory<EarlyBeanReferenceBeanB> referenceBeanBObjectFactory;
    @Autowired
    ObjectProvider<EarlyBeanReferenceBeanB> referenceBeanBObjectProvider;
    @Autowired
    EarlyBeanReferenceBeanB earlyBeanReferenceBeanB;
    @Autowired
    ObjectFactory<Collection<EarlyBeanReferenceBeanB>> collectionObjectFactory;
    @Autowired
    ObjectProvider<List<EarlyBeanReferenceBeanB>> collectionObjectProvider;
    @Value("${test.name}")
    private String name;
    @Value("#{1+1}")
    private String num;
    @Autowired
    EarlyBeanReferenceBeanB[] earlyBeanReferenceBeanBArray;
    @Autowired
    Collection<EarlyBeanReferenceBeanB> beanReferenceBeanBCollection;
    @Autowired
    List<EarlyBeanReferenceBeanB> beanReferenceBeanBList;
    @Autowired
    Set<EarlyBeanReferenceBeanB> beanReferenceBeanBSet;
    @Autowired
    Map<String,EarlyBeanReferenceBeanB> beanReferenceBeanBMap;

    @Autowired
    PersonService personService;

    public void test() {

    }

    @PostConstruct
    public void initMethod() {
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + earlyBeanReferenceBeanB);
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBOptional.get());
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBObjectFactory.getObject());
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBObjectProvider.getObject());
        System.out.println("========================");
        System.out.println(collectionObjectFactory.getObject().getClass());
        Collection<EarlyBeanReferenceBeanB> referenceBeanBCollection = collectionObjectFactory.getObject();
        referenceBeanBCollection.forEach(System.out::println);
        System.out.println("========================");
        System.out.println(collectionObjectProvider.getObject().getClass());
        for (EarlyBeanReferenceBeanB beanReferenceBeanB : collectionObjectProvider.getObject()) {
            System.out.println(beanReferenceBeanB);
        }
        System.out.println("========================");
        for (EarlyBeanReferenceBeanB beanReferenceBeanB : earlyBeanReferenceBeanBArray) {
            System.out.println(beanReferenceBeanB);
        }
    }


}
