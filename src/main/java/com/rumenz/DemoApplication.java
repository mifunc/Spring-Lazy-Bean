package com.rumenz;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class DemoApplication {
    public static  void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register(DemoApplication.class);
        ac.refresh();
        System.out.println("Spring 上下文启动完成。。。。。");
        //final DefaultRumenzFactory bean = ac.getBean(DefaultRumenzFactory.class);
        ac.close();
    }
    @Bean(initMethod = "initMethod")
    @Lazy
    public static  DefaultRumenzFactory defaultRumenzFactory(){
        return new DefaultRumenzFactory();
    }
}
