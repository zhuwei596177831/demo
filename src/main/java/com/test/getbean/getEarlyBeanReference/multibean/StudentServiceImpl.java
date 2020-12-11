package com.test.getbean.getEarlyBeanReference.multibean;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * @author 朱伟伟
 * @date 2020-11-30 16:55:19
 * @description
 */
@Service
//@Primary
@Priority(Ordered.HIGHEST_PRECEDENCE)
public class StudentServiceImpl implements PersonService {
}
