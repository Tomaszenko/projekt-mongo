package com.example.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String MAPPING_URL = "/";

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

}