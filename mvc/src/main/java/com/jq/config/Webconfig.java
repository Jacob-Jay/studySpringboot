package com.jq.config;

import com.jq.controller.AnnoController;
import com.jq.custom.ResponseBodyEncoding;
import com.jq.custom.interceptor.Myinterceptor1;
import com.jq.custom.oWNHandlerAdaptor;
import com.jq.custom.view.OwnHanlderMapping;
import com.jq.custom.view.resolver.ExcelViewResolver;
import com.jq.custom.view.resolver.JsonViewResolver;
import com.jq.custom.view.resolver.PdfViewResolver;
import com.jq.custom.view.resolver.XmlViewResolver;
import com.jq.dao.ViewResolverBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-16 21:53
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.jq.controller")
@Import(ResponseBodyEncoding.class)
public class Webconfig implements WebMvcConfigurer{

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }


    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver SpringResourceTemplateResolver = new SpringResourceTemplateResolver();
        SpringResourceTemplateResolver.setCharacterEncoding("utf-8");
        SpringResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        SpringResourceTemplateResolver.setCacheable(false);
        SpringResourceTemplateResolver.setPrefix("/WEB-INF/page/");
        SpringResourceTemplateResolver.setSuffix(".html");
        return  SpringResourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver());
        return springTemplateEngine;
    }

    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(springTemplateEngine());
        String[] excelue = {"test1","content"};
        resolver.setExcludedViewNames(excelue);
        registry.viewResolver(resolver);


    }

    @Bean
    public ViewResolver jspResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setViewNames("jsp*");
        internalResourceViewResolver.setOrder(-1);
        return internalResourceViewResolver;
    }


    @Bean
    public ViewResolver contentResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();

        resolver.setContentNegotiationManager(manager);
        List<ViewResolver> viewResolvers = new ArrayList<>();
        viewResolvers.add(pdfResolver());
        viewResolvers.add(jsonResolver());
        viewResolvers.add(excelResolver());
        viewResolvers.add(jaxb2MarshallingXmlResolver());
        resolver.setViewResolvers(viewResolvers);
       resolver.setOrder(Integer.MAX_VALUE);
        return resolver;
    }

    //@Bean
    public ViewResolver pdfResolver() {
        return new PdfViewResolver();
    }
    //@Bean
    public ViewResolver jsonResolver() {
        return new JsonViewResolver();
    }
    //@Bean
    public ViewResolver  excelResolver() {
        return new ExcelViewResolver();
    }
    //@Bean
    public ViewResolver jaxb2MarshallingXmlResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(ViewResolverBean.class);
        return new XmlViewResolver(marshaller);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Myinterceptor1());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //resolvers.add(new MyexceptionResover1());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //resolvers.add(new MyexceptionResover1());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /*configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.TEXT_HTML);*/
    }

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
       /* MultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();

        return standardServletMultipartResolver;*/

    }

    @Autowired
    public void add(RequestMappingHandlerMapping mapping, AnnoController controller) throws NoSuchMethodException {
        RequestMappingInfo info = RequestMappingInfo.paths("/annotation/config").params("name=jq")
                .methods(RequestMethod.GET).build();
        Method byConfig = AnnoController.class.getMethod("byConfig");
        mapping.registerMapping(info,controller,byConfig);
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("sdsd");

        for (HttpMessageConverter<?> messageConverter : converters) {
                if (messageConverter instanceof StringHttpMessageConverter) {
                    List<MediaType> mediaTypes = new ArrayList<>();
                    mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
                    mediaTypes.add(MediaType.APPLICATION_PROBLEM_JSON_UTF8);
                    ((StringHttpMessageConverter) messageConverter).setSupportedMediaTypes(mediaTypes);
                }
        }

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       // converters.add(new BufferedImageHttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }



    //测试自己写的handlerMapping和adaptor

    @Bean
    public HandlerMapping handlerMapping() {
        return new OwnHanlderMapping();
    }

    @Bean
    public HandlerAdapter handlerAdapter() {
        return new oWNHandlerAdaptor();
    }
    ////////


}
