package com.example.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Tomek on 12.05.2017.
 */
public class ServletContextFactory implements FactoryBean<ServletContext>, ServletContextAware{

    private ServletContext servletContext;

    @Override
    public ServletContext getObject() throws Exception {
        return servletContext;
    }

    @Override
    public Class<?> getObjectType() {
        return ServletContext.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
