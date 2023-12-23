package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someInterfaceWithMethods;
    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods) {
        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object;
        System.out.println(method.getName() + " in");
        if (method.getName().equals("stringMethodWithoutArgs")) {
            object = method.invoke(someInterfaceWithMethods, args);
        } else {
            object = method.invoke(someInterfaceWithMethods, args);
        }
        System.out.println(method.getName() + " out");

        return object;
    }
}
