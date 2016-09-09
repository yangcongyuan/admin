package com.etnlgravtnl.common.exception.MapperSupport;

import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by admin on 2016/6/24.
 */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Throwable>{
    private static final Logger logger = LoggerFactory.getLogger(ExceptionMapperSupport.class);

    @Override
    public Response toResponse(Throwable exception) {
        if(logger.isDebugEnabled())
        {
            exception.printStackTrace();
        }
        if(exception instanceof AbstractException ) {
            AbstractException exception1 = (AbstractException) exception;
            return Response.status(exception1.getHttpStatus().value()).entity("[{message:\""+exception1.getUserDefindExType()+".\"}]").type("application/json").build();
        }
       // return Response.status(500).entity("{exceptionMsg:\""+exception1.getUserDefindExType()+".\"}").type("application/json").build();
        return Response.status(500).entity("[{message:\""+ WebExceptionType.UNKNOWNEXCEPTION+".\"}]").type("application/json").build();
    }
}

