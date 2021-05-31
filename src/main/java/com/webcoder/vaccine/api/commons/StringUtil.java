package com.webcoder.vaccine.api.commons;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class StringUtil {

    public String getRandomElementFromStringArray (String[] arr) {
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }
}
