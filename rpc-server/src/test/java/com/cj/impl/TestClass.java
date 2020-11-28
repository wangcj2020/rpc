package com.cj.impl;

import com.cj.TestInterface;

import java.sql.SQLOutput;

public class TestClass implements TestInterface {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public void end() {
        System.out.println("end");
    }
}
