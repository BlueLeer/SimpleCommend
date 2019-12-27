package com.lee.commend.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author lee
 * @date 2019/12/27 15:05
 */
public class Test {
    public static void main(String[] args) {
        
        long l = Long.parseLong(null);
        System.out.println(l);

        Long ll = null;
        Long aLong = Optional.ofNullable(ll).orElse(0L);
        BigDecimal bigDecimal = BigDecimal.valueOf(aLong);
        System.out.println(bigDecimal);
    }
}
