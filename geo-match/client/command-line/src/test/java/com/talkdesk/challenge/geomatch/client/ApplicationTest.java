package com.github.christianfranco.geomatch.client;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Christian Franco on 17/12/2016.
 */
public class ApplicationTest {

    @Test @Ignore
    public void emptyArguments() throws Exception {
        Application.main();
    }

    @Test @Ignore
    public void invalidArguments() throws Exception {
        Application.main("--ERROR");
    }

    @Test @Ignore
    public void helpOption() throws Exception {
        Application.main("--help");
    }

    @Test @Ignore
    public void helpCallRestService() throws Exception {
        Application.main("+351265120000", "+351222220000", "+351211230000");
    }

    @Test @Ignore
    public void helpCallRestService_with_invalid_target() throws Exception {
        Application.main("+351260000", "+351222220000", "+351211230000");
    }

    @Test @Ignore
    public void helpCallRestService_with_no_customer_number() throws Exception {
        Application.main("+351265120000");
    }

    @Test @Ignore
    public void helpCallRestService_with_invalid_customer_number() throws Exception {
        Application.main("+351265120000", "+3512", "+351211230000");
    }
}