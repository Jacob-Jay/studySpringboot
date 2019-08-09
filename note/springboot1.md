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

- 将配置文件赋予bean，需要为容器成员并有set方法

  - @ConfigurationProperties("person") 
  - @PropertySource(value = "classpath:person.properties")+@value
  - @PropertySource(value = "classpath:person.properties")+@ConfigurationProperties("person") 
    - ${}环境参数
    - #{}spel表达式
    - “xx”字面量
  - ‘  \n ’原样输出即\n  
  - “\n” 代表换行

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

  

# 重要注解

- @SpringBootApplication 