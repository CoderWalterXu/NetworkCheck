package com.xlh.study.networkcheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Watler Xu
 * time:2020/9/3
 * description: 检测网络的注解
 * version:0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkCheck {
}
