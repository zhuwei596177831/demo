package com.example.springweb.databinder;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.http.MediaType;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-01-08 15:13:07
 * @description
 */
public class DataBindTest {
    public static void main(String[] args) {
//        testDataBinder();
//        testWebDataBinder();
        testCustomDataBinder();
    }


    /**
     * @author: 朱伟伟
     * @date: 2021-01-08 14:43
     * @description: 从源源码的分析中，大概能总结到DataBinder它提供了如下能力：
     * 把属性值PropertyValues绑定到target上（bind()方法，依赖于PropertyAccessor实现~）
     * 提供校验的能力：提供了public方法validate()对各个属性使用Validator执行校验~
     * 提供了注册属性编辑器（PropertyEditor）和对类型进行转换的能力（TypeConverter）
     * 还需要注意的是：
     * <p>
     * initBeanPropertyAccess和initDirectFieldAccess两个初始化PropertyAccessor方法是互斥的
     * 1. initBeanPropertyAccess()创建的是BeanPropertyBindingResult，内部依赖BeanWrapper
     * 2. initDirectFieldAccess创建的是DirectFieldBindingResult，内部依赖DirectFieldAccessor
     * 这两个方法内部没有显示的调用，但是Spring内部默认使用的是initBeanPropertyAccess()，具体可以参照getInternalBindingResult()方法~
     **/
    private static void testDataBinder() {
        DataBinderEntity dataBinderEntity = new DataBinderEntity();
        DataBinder dataBinder = new DataBinder(dataBinderEntity, "dataBinderEntity");
        //允许的字段
        dataBinder.setAllowedFields();
        //不允许的字段
        dataBinder.setDisallowedFields();
        //必须的字段
        dataBinder.setRequiredFields();
        Validator validator = new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return true;
            }

            @Override
            public void validate(Object target, Errors errors) {

            }
        };
        dataBinder.addValidators(validator);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "zww");
        propertyValues.add("age", "26");
        propertyValues.add("flag", "dd");
//        DataBinderInnerEntity dataBinderInnerEntity = new DataBinderInnerEntity();
//        dataBinderInnerEntity.setEmail("5653313@qq.com");
//        propertyValues.add("dataBinderInnerEntity", dataBinderInnerEntity);
//        propertyValues.add("dataBinderInnerEntity", "5653313@qq.com");
        dataBinder.bind(propertyValues);
        BindingResult bindingResult = dataBinder.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        System.out.println(dataBinder);
        System.out.println(dataBinderEntity);
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-08 15:22
     * @description: 单从WebDataBinder来说，它对父类进行了增强，提供的增强能力如下：
     * 支持对属性名以_打头的默认值处理（自动挡，能够自动处理所有的Bool、Collection、Map等）
     * 支持对属性名以!打头的默认值处理（手动档，需要手动给某个属性赋默认值，自己控制的灵活性很高）
     * 提供方法，支持把MultipartFile绑定到JavaBean的属性上~
     **/
    private static void testWebDataBinder() {
        WebDataBinderEntity webDataBinderEntity = new WebDataBinderEntity();
        WebDataBinder webDataBinder = new WebDataBinder(webDataBinderEntity, "webDataBinderEntity");
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("!stringArrays", new String[]{});
        propertyValues.add("_integerCollection", null);
        propertyValues.add("_flag", null);
        webDataBinder.bind(propertyValues);
        BindingResult bindingResult = webDataBinder.getBindingResult();
        System.out.println(webDataBinderEntity);
    }


    private List<MultipartFile> createMultipartFile() {
        return Collections.singletonList(new MultipartFile() {
            @Override
            public String getName() {
                return "testListFile";
            }

            @Override
            public String getOriginalFilename() {
                return "wvs.jpg";
            }

            @Override
            public String getContentType() {
                return MediaType.IMAGE_JPEG_VALUE;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 1024;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[1024];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
    }

    private static void testCustomDataBinder() {
        CustomDataBinderEntity customDataBinderEntity = new CustomDataBinderEntity();
        DataBinder dataBinder = new DataBinder(customDataBinderEntity, "customDataBinderEntity");
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.add("startDate", "Fri Jan 08 16:01:18 CST 2021");
        propertyValues.add("endDate", new Date());
//        propertyValues.add("dateStr", "2021-01-08 16:02:29");
        propertyValues.add("dateStr", "2021-01-08");
        propertyValues.add("localDate", LocalDate.now());
        propertyValues.add("localTime", LocalTime.now());
        propertyValues.add("localDateTime", LocalDateTime.now());
        dataBinder.registerCustomEditor(Date.class, new DatePropertyEditor());
        dataBinder.bind(propertyValues);
        System.out.println(customDataBinderEntity);
    }


}
