package com.example.schedule.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Component
public class ScheduledExecutionMethod {

    public void executeWork(String work) {
        try {
            Method method = this.getClass().getDeclaredMethod(work);
            method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public  void work(){
        System.out.println("1111");
    }

    public  void work1(){
        System.out.println("2222");
    }

    public  void work2(){
        System.out.println("3333");
    }

    public  void work3(){
        System.out.println("4444");
    }

    public  void work4(){
        System.out.println("555");
    }

    public  void work5(){
        System.out.println("6666");
    }
}
