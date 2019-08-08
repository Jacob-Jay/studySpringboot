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

