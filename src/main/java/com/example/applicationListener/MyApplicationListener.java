package com.example.applicationListener;

import com.example.applicationEvent.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-10-23 15:43:48
 * @description
 * @see org.springframework.context.support.ApplicationListenerDetector
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("MyApplicationListener==========>" + event.getSource());
    }
}
