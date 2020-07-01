package com.rumenz;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultRumenzFactory implements  InitializingBean {

    public DefaultRumenzFactory() {
        System.out.println("无参构造方法执行....");
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct init.......");
    }
    public void initMethod(){
        System.out.println("init method.......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet.....");
    }
}
