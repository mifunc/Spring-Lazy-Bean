- XML 配置:<bean lazy-init=”true” ... />
- Java 注解:@Lazy(true)

> Spring 中默认是非延迟加载Bean的,也就是提前把Bean初始化好,用的时候直接用. 优点是运行的时候比较快(提前初始化了,直接用). 缺点是启动慢和占用内存,因为要初始化很多Bean.

>延迟加载是需要的时候再去初始化Bean. 优点是解约内存,启动快(不需要提前初始化Bean). 缺点是运行的时候比较慢(用的时候先要初始化才能用).

### @Lazy开启Bean延迟加载

**DemoApplication.java**
```java
package com.rumenz;
public class DemoApplication {
    public static  void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register(DemoApplication.class);
        ac.refresh();
        System.out.println("Spring 上下文启动完成。。。。。");
        //开启了延迟加载,调用的时候才会初始化
        final DefaultRumenzFactory bean = ac.getBean(DefaultRumenzFactory.class);
        ac.close();
    }
    @Bean(initMethod = "initMethod")
    @Lazy //开启延迟加载
    public static  DefaultRumenzFactory defaultRumenzFactory(){
        return new DefaultRumenzFactory();
    }
}

```

**DefaultRumenzFactory.java**

```java
package com.rumenz;
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

```

**调用ac.getBean(DefaultRumenzFactory.class) 输出**

```
Spring 上下文启动完成。。。。。
xxx.DefaultListableBeanFactory - Creating shared instance of singleton bean 'defaultRumenzFactory'
无参构造方法执行....
PostConstruct init.......
afterPropertiesSet.....
init method.......


```
**不调用ac.getBean(DefaultRumenzFactory.class) 输出**

```
Spring 上下文启动完成。。。。。  //没有调用,所以没有执行初始化
```

源码:https://github.com/mifunc/Spring-Lazy-Bean


原文: [https://rumenz.com/rumenbiji/Spring-Lazy-Bean.html](https://rumenz.com/rumenbiji/Spring-Lazy-Bean.html)
