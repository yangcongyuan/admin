package com.etnlgravtnl.common.exception.MapperSupport;

import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import org.springframework.http.HttpStatus;

/**
 * Created by admin on 2016/6/24.
 */
public abstract class AbstractException extends RuntimeException{


    public AbstractException(Throwable ex){
        super(ex);
    }



    public abstract WebExceptionType getUserDefindExType();
    public abstract HttpStatus getHttpStatus();

}
