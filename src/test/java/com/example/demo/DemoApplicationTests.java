package com.example.demo;


import com.example.configuration.TestConfiguration;
import com.example.configuration.scan.TestScanBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
class DemoApplicationTests {

    //    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
    }


}
