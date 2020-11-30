package com.example.getbean.getEarlyBeanReference.multibean;

import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
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
