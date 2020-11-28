package com.cj;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest extends TestCase {

    @Test
    public void testToString() {
        Request request = new Request();
        System.out.println(request );
    }
}