package com.jq.config;

import com.jq.custom.filter.Myfilter2;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-16 21:50
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{Webconfig.class};
    }



    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("jq", "sdsd");
        servletContext.setInitParameter("jq2", "sdsd");

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("tes", new HttpServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                System.out.println("service");
            }
        });
        dynamic.addMapping("/test");
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);

        //servlet原生文件上传
       /* MultipartConfigElement configElement = new MultipartConfigElement("D:\\test",
                1024*1000,1024*1000,1024*10);
        registration.setMultipartConfig(configElement);*/
    }

/* @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
//        servletContext.addListener(new MyListener2());
        servletContext.addListener(new MyListener());
    }*/


    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8",true);

        Filter[] filters =  new Filter[]{encodingFilter};
        return  filters;
    }


    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return super.createDispatcherServlet(servletAppContext);
    }


}
