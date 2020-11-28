package com.cj;

import com.cj.common.utils.ReflectionUtils;
import com.cj.impl.TestClass;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    private ServiceManager serviceManager;

    @Before
    public void init(){
        this.serviceManager = new ServiceManager();
        testRegister(); //运行testLookup时要先注册
    }

    @Test
    public void testRegister() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class,bean);
    }

    @Test
    public void testLookup() {
        Request request = new Request();
        Method method = ReflectionUtils.getAllPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);
        request.setService(serviceDescriptor);
        ServiceInstance serviceInstance = serviceManager.lookup(request);
        System.out.println(serviceInstance);
        assertNotNull(serviceInstance);
    }
}