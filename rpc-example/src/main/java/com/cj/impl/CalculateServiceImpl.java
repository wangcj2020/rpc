package com.cj.impl;

import com.cj.CalculateService;

public class CalculateServiceImpl implements CalculateService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }
}
