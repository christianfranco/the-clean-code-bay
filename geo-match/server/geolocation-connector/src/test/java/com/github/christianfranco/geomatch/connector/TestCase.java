package com.github.christianfranco.geomatch.connector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Christian Franco on 14/12/2016.
 */
public abstract class TestCase {
    private final ApplicationContext context;

    public TestCase() {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    public ApplicationContext getContext() {
        return context;
    }
}
