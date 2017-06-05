package com.example.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.util.EnumSet;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String MAPPING_URL = "/";

    @Autowired
    ServletContext servletContext;

    private int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);
//        servletContext.setAttribute("sessionTrackingModes", EnumSet.of(SessionTrackingMode.COOKIE));
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("in config");
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMVCConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("dispatcher");
        return new String[] { MAPPING_URL };
    }

//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//
//        // upload temp file will put here
//        File uploadDirectory = new File("C:/Users/Tomek");
//
//        // register a MultipartConfigElement
//        MultipartConfigElement multipartConfigElement =
//                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
//                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
//
//        registration.setMultipartConfig(multipartConfigElement);
//    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
        charFilter.setEncoding("UTF-8");
        charFilter.setForceEncoding(true);
        return new Filter[] { new HiddenHttpMethodFilter(), charFilter,
                new HttpPutFormContentFilter() };
    }
}