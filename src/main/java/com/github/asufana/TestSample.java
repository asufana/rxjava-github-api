package com.github.asufana;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class TestSample {
    
    @Test
    public void test() throws Exception {
        assertThat(true, is(true));
    }
}
