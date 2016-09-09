package com.etnlgravtnl.common.persistence.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2016/6/20.
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface TokenCheck {

}
