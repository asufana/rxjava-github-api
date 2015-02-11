package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.slf4j.*;

public class TestSample {
    
    private static final Logger logger = LoggerFactory.getLogger(TestSample.class);
    
    @Test
    public void test() throws Exception {
        assertThat(true, is(true));
        logger.error("Log Test");
    }
}
