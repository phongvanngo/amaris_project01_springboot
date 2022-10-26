package com.example.novapo_practice05.common;

public class MyUtil {
    public static Long randomLong(Long leftLimit,Long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
