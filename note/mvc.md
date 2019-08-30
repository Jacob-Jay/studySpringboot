# servlet容器

## servlet

定义了服务器与java代码之间运行的规范，即通过url该怎么找到调用的类以及调用的方法

通过web.xml映射

### 生命周期

constructor------init-------service------destroy

默认第一次请求时才初始化并应用，知道服务器停止菜同内存中销毁

如果设置了on-start-up 就在启动时就初始化

### req与resp

- 请求

  - 请求头
    - 区分方法
    - url与uri
  - 请求行
  - 请求正文

- 响应

  

## 启动顺序

### servletContext

创建servletContext读取contextParam作为参数

### listener  

不同特性的listener拥有不同的特性，参与session、request、context的生命周期，初始化顺序根据web。xml的声明顺序，也可以使用@webListener注解配置，但是顺序未知。

- ServletContextListener   	在context初始化与销毁时做一些事
- ServletContextAttributeListener
- ServletRequestListener
- ServletRequestAttributeListener
- HttpSessionListener
- HttpSessionAttributeListener
- HttpSessionIdListener

### filter   

创建filter   对请求进行拦截  需要配置拦截规则  url？或者servletname

使用@webFilter或者初始化容器时的getFilter()方法

### servlet

规定了运行在服务器中的java小程序的接口规范，只要按照规范实现就可以在服务器中运行

- ```java
   ServletRegistration.Dynamic own = servletContext.addServlet("own", new MyServlet());
   own.addMapping("/own");      //往web容器中添加servlet并映射到/own中
  ```

- 

# 类型转换与校验

## 转换

### propertyEditor（低级）

用于字符串与特定类型之间的转换

1. BeanWrapper
2. 主要方法
   1. setAsText
   2. getAsText

### convert（高级）

用于类型与类型之间的转换

1. core.convert.support 
   1. Converter  将指定类型转换为指定类型
   2. ConditionalConverter   对源类型和目标类型进行判断满足条件才转换
   3. GenericConverter 有多个对应关系，可以进行复杂的转换不仅仅是一对一，通常也是先了ConditionalConverter   
   4. ConverterFactory 生产转转换器的工厂
   5. ConverterRegistry  添加转换器
   6. ConversionService  判断能否转换以及进行转换（类型和TypeDescriptor）
2. org.springframework.format
   1. Printer  以字符串方式展示对象可以根据地区信息
   2. Parser 将字符串转换为指定类型可以使用地区信息
   3. Formatter 继承了printer和parser
   4. AnnotationFormatterFactory  生产能解析format注解的format
   5. FormatterRegistry  继承了converterRegistry   添加formatter
   6. FormatterRegistrar注册FormatterRegistry  

## 校验

### Validator

# springmvc

将所需的类放在spring容器中，往servlet容器注册一个分发的servlet，以及listener完成容器和所需资源的加载

## 初始化

通过SpringServletContainerInitializer的注解@HandlesTypes(WebApplicationInitializer.class)在类路径中查找对应的类及其子类（注意不能时接口或者抽象类，根据order对其进行排序，循环调用onStartup方法初始化）

- 加载根容器并注册一个listener到container
- 加载dispatcherServlet，配置mapping以及filter
- 通过listener初始化根容器，ContextLoader
- 通过servletinit方法实例化web容器
- 配置各种resolver和handler
  - ![1566369859907](initMvc)

## 请求处理

- ### filter过滤
- ### processRequest
  
  - ##### 获取之前信息
  - ##### doservice
    
    - request设置相关attribute
    - dodispatch
      1. 校验是否上传文件
      2. 根据handlerMapping获取HandlerExecutionChain
         
         - ![1566379306597](/handlerExcuteChain)
      3. 根据handler获取handlerAdaptor（ha.supports(handler)）
      4. interceptore前置处理
      5. adaptor.handle
         - checkRequest(request);
         - 获取视图（注意可以控制session的同步与否）
           1. WebDataBinderFactory  （数据转换器工厂@initBInder）
           2. ModelFactory（SessionAttributes，ModelAttribute）
           3. 复制给ServletInvocableHandlerMethod，并设置参数返回值等处理器
           4. 常见模型容器mavContainer并初始化模型赋值
           5. ServletInvocableHandlerMethod.invokeAndHandle(webRequest, mavContainer);
             - 获取返回值
               - 获取参数（循环遍历参数使用匹配的argumentResolver获取）
                 - 获取参数名称
                 - 模型中是否存在，模型中不存在先从url中获取，没获取到再从request获取进行类型转换返回，若果还没获取到并且是无参构造通过构造器创建一个对象（ConstructorProperties）
                 - 数据绑定
               - 执行controller的方法
             - 设置响应状态（ResponseStatus）
             - 处理返回值
               1. 返回值为null或者返回json等特殊需求直接返回
               2. returnValueHandlers.handleReturnValue
                  1. 遍历选择处理器
                  2. 将响应写入response（messageConverters）
           6. 获取视图
              - 更新模型数据
         - 是由需要缓存
      6. 如果需要获取视图名称
      7. interceptor后置处理
      8. 视图渲染
         1. 获取区域信息
         2. viewResolver解析获得view
      9. interceptor完成回调
    - 异步操作
  - #### 还原信息

## 知识点

### HttpServletBean

获取web.xml中的init参数

### FrameworkServlet

整合springcontext与servlet以及处理请求发布相应事件

- 容器配置文件
- 容器
- 发布事件
- 扩展配置容器
- 放置请求和local信息至threadLocal

### DispatcherServlet

将极大组件组合起来调度完成整个请求的处理

1. 配置handlerMapping将请求映射到handler，可以在web容器中配置
2. 配置handlerAdaptor使用habdler处理请求，配置在app容器中？？？
3. 配置exceptionResolver使用habdler处理请求，配置在app容器中？？？
4. 配置viewResolver将视图名转换为视图，配置在app容器中？？？
5. 上传文件
6. 国际化
7. 主题



### interceptor

WebMvcConfigurationSupport注入handlerMapping时注入的

```java
@EnableWebMvc
@Configuration
@ComponentScan("com.jq.controller")
public class Webconfig implements WebMvcConfigurer{   
    @Override   
    public void addInterceptors(InterceptorRegistry registry) {       		 			           registry.addInterceptor(new Myinterceptor1());   
     }
}
//通过重写该方法添加interceptor，因为往容器中注入handlerMapping时会调用该方法设置属性
//对于返回json的postHandle无法修改response因为response已经写出去了
//前置处理可以设置响应的状态码
```

### HandlerExceptionResolver

```java
    @Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		List<HandlerExceptionResolver> exceptionResolvers = new ArrayList<>();
		configureHandlerExceptionResolvers(exceptionResolvers);//web配置类从写添加
		if (exceptionResolvers.isEmpty()) {  //没有就添加默认的
			addDefaultHandlerExceptionResolvers(exceptionResolvers);
		}
		extendHandlerExceptionResolvers(exceptionResolvers);   //web配置类继续添加
		HandlerExceptionResolverComposite composite = new           HandlerExceptionResolverComposite();
		composite.setOrder(0);
		composite.setExceptionResolvers(exceptionResolvers);
		return composite;
	}
```

WebMvcConfigurationSupport装配一个HandlerExceptionResolverComposite注入容器，以上代码配置的全在组合中，也可以单独往容器中配置与之并列存在。

可以根据异常和处理器定制并返回一个modelAndView，或者返回null当异常为解决时

- ResponseStatus
- ExceptionHandler  在没配置解析器的时候可以使用
- Controller
- ControllerAdvice




### viewResolver

```java
@Bean
	public ViewResolver mvcViewResolver() {
		ViewResolverRegistry registry = new ViewResolverRegistry(
				mvcContentNegotiationManager(), this.applicationContext);
		configureViewResolvers(registry);  //注册解析器
		//如果没有注册就添加默认的
		if (registry.getViewResolvers().isEmpty() && this.applicationContext != null) {
			String[] names = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
					this.applicationContext, ViewResolver.class, true, false);
			if (names.length == 1) {
				registry.getViewResolvers().add(new InternalResourceViewResolver());
			}
		}

		ViewResolverComposite composite = new ViewResolverComposite();
		composite.setOrder(registry.getOrder());
		composite.setViewResolvers(registry.getViewResolvers());
		if (this.applicationContext != null) {
			composite.setApplicationContext(this.applicationContext);
		}
		if (this.servletContext != null) {
			composite.setServletContext(this.servletContext);
		}
		return composite;
	}
```

1. 可以添加多个视图解析器，但是主要排除其他解析器的视图，不然会报错

   - thymeleaf设置 resolver.setExcludedViewNames(excelue)排出不能处理的

   - InternalResourceViewResolver通过setViewNames("jsp*");设置能处理的

2. 配置的order无效，只有容器中的order才有效

3. forward:xx转发请求

   1. /xxxx=context/xxxx
   2. xxx = context/this/xxxx  加载controller类注释后面

4. redirect:重定向

5. 内容匹配即通过后缀pdf、json等匹配

### localeResolver？？？

### themeResolver？？？

### Multipart Resolver

上传文件有两种方式

1. 使用servlert原生方式需3.0+

   ```java
     @Override
       protected void customizeRegistration(ServletRegistration.Dynamic registration) {
           super.customizeRegistration(registration);
   
           //servlet原生文件上传
           MultipartConfigElement configElement = new MultipartConfigElement("D:\\test",
                   1024*1000,1024*1000,1024*10);
           registration.setMultipartConfig(configElement);
       }
   ```

   

2. 使用apach-Common

   ```java
   @Bean
       public MultipartResolver multipartResolver() {
           CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
           try {
               Resource resource = new FileUrlResource("D:\\test");
               commonsMultipartResolver.setUploadTempDir(resource);
               commonsMultipartResolver.setMaxUploadSize(1024*1024*5);
               commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024);
               commonsMultipartResolver.setMaxInMemorySize(1024*3);
           } catch (Exception e) {
               e.printStackTrace();
           }
           return commonsMultipartResolver;
   
       }
   ```



### filter

1. ​	FormContentFilter：通过post请求携带参数提交put和delete等请求

   ```java
   //表单采用post方式隐藏携带参数——method
   <form th:action="@{/filter/submit}" method="post">
       <input type="hidden" name="_method" value="put">
       <input type="text" name="name">
       <input type="submit" value="ok">
   </form>
   
   //配置一个filter包装请求进行适配
   @WebFilter(urlPatterns = "/*")
   public class MethodTransFilter extends HiddenHttpMethodFilter {
   }
   
   ```

   

2. ForwardedHeaderFilter：

### handlerMapping

映射请求和处理器的关系，即根据请求返回一个包含处理器和拦截器的符合对象

![1566867278903](/handlerMapping)

![1566987796534](/requestHandlerMapping)

org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#initHandlerMethods

#### requestMapping

- 使用正则

- 使用ant风格

- 使用consumes限定content-type的值producer限定accept的值

- 使用head限定head参数范围

- 使用param限定参数范围  =    !=

- 可以自定义注解用于使用

- 可以通过代码进行映射，著需要声明方法

  ```java
   @Autowired
      public void add(RequestMappingHandlerMapping mapping, AnnoController controller) throws NoSuchMethodException {
          RequestMappingInfo info =    		                  RequestMappingInfo.paths("/annotation/config").
              	params("name=jq")
                  .methods(RequestMethod.GET).build();
          Method byConfig = AnnoController.class.getMethod("byConfig");
          mapping.registerMapping(info,controller,byConfig);
      }
  ```

  

#### handlerMethod

1. 参数可以有

  - httpSession注意不是线程安全的，多请求共享时需要将synchronizeOnSession设为true保证线程安全

2. 返回值

3. 类型转换

4. matrix 必须跟在pathvarible后面

5. requestParam

6. RequestHeader

7. CookieValue

8. modelAttribute
  - 使用在方法上会在请求处理使用,可以向普通请求一样注入或者数据绑定

    - 返回一个数据会添加到到模型中

    - 注入模型对其进行操作

      ```java
      @ModelAttribute
      public Data add() {
      	return new Data(name,msg);
      }
      @ModelAttribute
      public void populateModel(@RequestParam String number, Model model) {
      model.addAttribute(accountRepository.findAccount(number));
      // add more ...
      }
      ```

      

  - 使用在参数上从模型中获取，没有就创建并添加，bingding为true时即使存在也会进行绑定并覆盖，即更新；但是binging为fasle就不会

9. SessionAttributes

  - model中第一次创建之后放入session以备使用，在方法处理完成后才放入。可以使用SessionStatus.setComplete()清除

10. sessionAttribute   获取session总的属性

11. requestAttribute  获取request中的属性

12. RedirectAttributes 
   - atribute  拼接在url中   常规获取
   - flashAttribute  放在？？？？  通过model获取

13. flashAttibute 

   - 从定向传参放在session中一到页面就移除，使用flashMap保存

14. requestBody 将json转换为参数

15. httpEntity

16. ResponseEntity   直接返回请求，类似于@responseStatus+@Responsebody

#### dataBInder

1. formatters
2. converters

#### controllerAdvice

​	用于全局定义@sessionAttributes  @modelAttribute @initBInder @exceptionHandlers等适用于所有controller可以通过属性进行窄化

### HandlerAdaptor

![1567129927133](/handlerAdaptor1)

![1567129979106](/handleradaptor2)

#### 知识点

1. convert
2. webbindInitialization
3. HandlerMethodArgumentResolver(参数解析器)
4. HandlerMethodReturnValueHandler

### ApplicationContextInitializer

堆上下文进行

### 





## 疑问点

- viewresolver先从web找再从root找
- 内容协商ContentNegotiationConfigurer