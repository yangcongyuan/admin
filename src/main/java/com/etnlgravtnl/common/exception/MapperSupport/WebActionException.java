package com.etnlgravtnl.common.exception.MapperSupport;

import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import org.springframework.http.HttpStatus;

/**
 * Created by admin on 2016/6/24.
 */
public class WebActionException extends AbstractException {
    protected WebExceptionType type;
    protected Object data;
    protected HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public WebActionException(Throwable ex, WebExceptionType type, Object data){
        super(ex);
        this.type = type;
        this.data = data;
    }
    public WebActionException(HttpStatus httpStatus, Throwable ex, WebExceptionType type, Object data)
    {
        super(ex);
        this.httpStatus=httpStatus;
        this.type = type;
        this.data = data;
    }


    public WebActionException(WebExceptionType type, Object data){
        this(HttpStatus.INTERNAL_SERVER_ERROR,null,type,data);
    }
    @Override
    public WebExceptionType getUserDefindExType(){
        return this.type;
    }
    public  Object getData(){
        return  this.data;
    }

    public static void main(String[] args) {
        System.out.println(HttpStatus.EXPECTATION_FAILED.value());
    }

}
