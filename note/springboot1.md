# 快速开始

```xml
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.1.7.RELEASE</version>
</parent> 
```

- 定义依赖的基础版本

- ```java
  @RestController
  @EnableAutoConfiguration   
  public class Example {
      @RequestMapping("/")
      String home() {
          return "Hello World!";
      }
  
      public static void main(String[] args) {
          SpringApplication.run(Example.class, args);
      }
  
  }
  ```

  - EnableAutoConfiguration表示自动配置spring各种依赖
  -  SpringApplication.run(Example.class, args);交由SpringApplication引导启动项目并指明example为主要类，args传递虚拟机参数

# 使用

## 构建系统

- 依赖管理
  - springboot制定了相应的依赖版本，最好不要更改，确实需要可以覆盖
- 快速开始  spring-boot-starter-*

## code

## config

- 使用单一程序入口。其他配置类通过impor或者自动扫描
- 如果要使用xml使用importResource导入

## 自动配置

- @EnableAutoConfiguration或 @SpringBootApplication开启自动配置
- 主动声明bean会回退自动配置的对应功能
- 排出不需要的自动配置
  - @EnableAutoConfiguration（exclude = {DataSourceAutoConfiguration.class}）
  - @SpringBootApplication(excludeName = "xx.xx.xx")全限定名
  - 配置文件spring.autoconfigure.exclude   全限定名

## 依赖注入

- 使用spring 那一套 如autowired  service等

- 如果声明的属性在bean的构造方法中则不同使用autowired

  - ```java
    @Bean
    public class a{
    	private B b;
    	public a(B b){
    		this.b = b;
    	}
    }
    ```

## @SpringBootApplication

等价于以下三个注解，可以选择使用部分

- @SpringBootConfiguration  注册配配置类
- @EnableAutoConfiguration   自动配置
- @ComponentScan    报扫描

## spring-boot-devtools 

- 开发工具，可以实现自动重启等

# 特性

## SpringApplication

- FailureAnalyzer	启动失败分析

- banner

  - banner.txt

  - banner.jpg png gif

  - - 默认在类路径下

    - 先打印图片在文字

    - 可以通过yaml文件指定文件

    - ```
      spring:
        banner:
          location: classpath:/banner1/banner.txt
          image:
            location: classpath:/xx/xx.jpg
      ```

- 自动义启动项

  -  

  ```java
  public static void main(String[] args) {
          SpringApplication application = new SpringApplication(SpbApplication.class);
           application.setBannerMode(Banner.Mode.OFF);
          application.run(args);
      }
  ```

  可以使用在启动时配置一些属性，但是yaml配置文件貌似会覆盖这里配置的

- 父子容器启动

  - ```java
    public static void main(String[] args) {
            new SpringApplicationBuilder()
                    .parent(SpbApplication.class)
                    .bannerMode(Banner.Mode.OFF)
                    .run(args);
        }
    ```

  但是貌似不起作用

- 容器类型

  - 默认使用AnnotationConfigApplicationContext
  - 存在mvc就使用AnnotationConfigServletWebServerApplicationContext
  - 存在webflux则使用AnnotationConfigReactiveWebServerApplicationContext
  - 同时存在mvc与webflux时使用AnnotationConfigServletWebServerApplicationContext，但是也可以设置setWebApplicationType(WebApplicationType)决定使用哪一种web容器
  - 甚至可以setApplicationContextClass(…)设置容器类型，不论是普通环境还是web环境

- 获取启动参数

  - ```java
    @Component
    public class ArgsBean {
       // @Autowired
        public ArgsBean(ApplicationArguments args) {
            boolean debug = args.containsOption("a");
            System.out.println("sssssssssssssssssssssssssss      "+debug);
            String[] sourceArgs = args.getSourceArgs();
            for (String sourceArg : sourceArgs) {
                System.out.println(" param2           "+sourceArg);
            }
        }
    }
    ```

    但是不是很明白

- 启动之后做一些工作

  ​	相同order时ApplicationRunner比CommandLineRunner先执行，但是可以指定order确认先后执行顺序

  - ApplicationRunner

    - ```java
      @Component
      @Order(10)
      public class AppRunner implements ApplicationRunner {
          @Override
          public void run(ApplicationArguments args) throws Exception {
              System.out.println("++++++++++++++AppRunner++++++++++");
              String[] sourceArgs = args.getSourceArgs();
              for (String sourceArg : sourceArgs) {
                  System.out.println(sourceArg);
              }
              System.out.println("++++++++++++++AppRunner++++++++++");
          }
      }
      ```

  - CommandLineRunner

    - ```java
      @Component
      @Order(10)
      public class CommondRead implements CommandLineRunner {
          @Override
          public void run(String... args) throws Exception {
              System.out.println("+++++++++++CommondRead++++++++++++");
              for (Object arg : args) {
                  System.out.println(arg);
              }
              System.out.println("+++++++++++++CommondRead+++++++++++");
          }
      }
      ```

- 退出

  - ExitCodeGenerator

    - 查询日志了解系统中止的原因

  - ExitCodeExceptionMapper

    - 根据异常返回不同的code

  - 通过发送请求关闭程序

    ```java
    @Controller
    public class ExitController implements ApplicationContextAware{
    
        private ApplicationContext applicationContext;
    
        @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
        public void shutdown() {
            final int exitCode = 0;
            ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {
                @Override
                public int getExitCode() {
                    System.out.println("tuichu.........");
                    return exitCode;
                }
    
            };
            //退出程序并从声明的ExitCodeGenerator获取退出code
            SpringApplication.exit(applicationContext, exitCodeGenerator);
        }
    
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) 		throws BeansException {
            this.applicationContext = applicationContext;
        }
    
    }
    ```

## 配置文件

### 加载顺序

- 优先级由高到低，高的覆盖低的

  -  
  - 命令行参数 如--person.name=ppp
  - servletconfig参数
  - servletcontext参数
  - jndi
  - java系统变量
  - os环境变量
  - RandomValuePropertySource
  - 打的jar包外 application-{dev}.yml/properties
  - 打的jar包中 application-{dev}.yml/properties
  - 打的jar包外 application.yml/properties
  - 打的jar包中 application.yml/properties
  - @PropertySource指定的配置文件
  - 启动时通过代码设置

- 随机值

  - ```properties
    person.num = ${random.int(9,10)}			//均表示范围内的整数随机值
    person.num2 = ${random.int[1024,65536]}
    ```
  
- application加载

  - 没有指定位置就按优先级进行覆盖
  - ![1565657877573](/1565657877573.png)
    - 优先级 1>2>3>4即优先级高的覆盖优先级低的
    - 1 为项目所在目录的config目录下 2 为项目所在目录直接下
  - 指订了位置就只加载指定位置的配置文件，默认位置失效，可以通过环境参数配置文件的名称或者位置
    - ![1565664083737](/1565664083737.png)

  - 指定spring.config.additional-location环境变量添加额外的配置文件位置，优先级高于默认的

### 语法

- 将配置文件赋予bean，需要为容器成员并有set方法

  - 无论yaml还是properties都忽略—  __  大小写
    - 即  name=NAME=Na-m_e
  - @ConfigurationProperties("person") 
    - 不仅可以用在类上注入bean，也可以用在@bean的方法上
  - @PropertySource(value = "classpath:person.properties")+@value
  - @PropertySource(value = "classpath:person.properties")+@ConfigurationProperties("person") 

- @EnableConfigurationProperties可以对使用的ConfigurationProperties注解类进行注入到容器，而不用使用component

- @value注解

  - ${}环境参数
  - #{}spel表达式
  - “xx”字面量

- 配置文件是否转义

  - ‘  \n ’原样输出即\n  
  - “\n” 代表换行

- 占位符

  - app:
      id: 111
      name: yml
      desc: ${app.name}描述  // 等价于yml描述

- 集合或者数组

  - ```yaml
    app:
      titles:
        - yml1
        - yml2
    ```

  - ```properties
    app.titles[0] = title1
    app.titles[1] = title2
    ```

- yml一个文件多部分

- ```yaml
  server:
    port: 8083
  spring:
    profiles:
  	active: dev
  ---
  spring:
    profiles: dev						
  server:
    port: 8082
  ---
  spring:
    profiles: pro
  server:
    port: 8084
  ```

- 类型转换

  - @ConfigurationProperties

    - 实现convert接口注册为bean，并使用ConfigurationPropertiesBinding修饰

      - ```java
        @ConfigurationPropertiesBinding
        @Component
        public class StringToDate implements Converter<String,Date> {
            @Override
            public Date convert(String source) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                Date parse = null;
                try {
                    parse = simpleDateFormat.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return parse;
            }
        }
        ```

        

    - 注册一个ConversionService

      - ```java
        @Bean
            public ConversionService conversionService() {
                ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
                Set<Converter> converters = new HashSet<>();
                converters.add(new StringToDate());
               // converters.add(new IntegerToDateConverter());
                bean.setConverters(converters);
                bean.afterPropertiesSet();
                return bean.getObject();
        ```

    -  注册一个CustomEditorConfigurer

      - ```java
        public class StringToDateCustomEditorConfigurer extends PropertyEditorSupport
               @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                Date parse = null;
                try {
                    parse = simpleDateFormat.parse(text);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setValue(parse);
            }
        }
        ```

      - ```java
        @Bean
            public CustomEditorConfigurer customEditorConfigurer() {
                CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
                Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
                customEditors.put(Date.class, StringToDateCustomEditorConfigurer.class);
                customEditorConfigurer.setCustomEditors(customEditors);
                return  customEditorConfigurer;
            }
        ```

  - @value

    - 除了convert同上

## profile

使用

- application.setAdditionalProfiles("redisPro");
- spring.profiles.include  不会被覆盖

来激活额外的配置文件，根据激活的文件注入不同的bean，列如生产数据库、开发数据库

![1565699196542](/1565699196542.png)



## log

## 国际化

## json

## web开发？？？？？？？？

- HttpMessageConverters

  消息转换

  HttpMessageConverter

- JsonComponent

- MessageCodesResolver

- 静态内容 

  - 默认位置优先级从高到底

    /META-INF/resources	>	resources	>	static	>	public

  - spring.mvc.static-path-pattern = /web/**  

    - 只能填写一个
    - 不能是/webjars/**  因为这是默认寻找jar引入静态资源的路径
    - 对url进行重定向，如/web/index.html至/index.html在静态资源下去找，并且只有满足/web/**模式的url才会进行静态资源映射，也就是设置了直接访问/index.html会404

  - spring.resources.static-locations

    - 将覆盖默认的静态资源位置，到指定的目录寻找静态资源

- webjar引入js

  - maven添加依赖

    - ```xml
      <dependency>
      			<groupId>org.webjars</groupId>
      			<artifactId>jquery</artifactId>
      			<version>3.3.1</version>
      		</dependency>
      ```

  - ```
    http://localhost:8083/webjars/jquery/3.3.1/jquery.js
    ```

    - /webjar开始的url都去 classpath:/META-INF/resources/下寻找

### 路径匹配与内容协商？？？？？？

- 禁止后缀匹配，如index.do 不能匹配待index
- 模板
  - 添加依赖
  - 配置文件配置属性
    - 缓存
    - 编码
    - 前后缀
- 错误处理

## 权限控制

## 数据库

# 重要注解

- @SpringBootApplication 