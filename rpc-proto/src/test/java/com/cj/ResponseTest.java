package com.cj;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest extends TestCase {

    @Test
    public void testToString() {
        Response response = new Response();
        System.out.println(response);
    }
}