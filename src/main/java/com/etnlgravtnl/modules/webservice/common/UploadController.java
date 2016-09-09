package com.etnlgravtnl.modules.webservice.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import com.etnlgravtnl.common.config.Global;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.exception.MapperSupport.WebActionException;

/**
 * Created by guozheng on 2016/6/23.
 */
@Component
@Path("/common")
public class UploadController {
    /**
     * Constants operating with images
     */
    private static final String ARTICLE_IMAGES_PATH = Global.getConfig("imagesPath");

    /**
     * 图片上传
     *
     * @param fileInputStream
     * @param disposition
     * @return
     */
    @POST
    @Path("uploadimage/{type}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadimage(
                                @Context HttpServletRequest request,
                                @PathParam("type") String type,
                                @FormDataParam("file") java.io.InputStream fileInputStream,
                                @FormDataParam("file") FormDataContentDisposition disposition) {
    	String uuid = UUID.randomUUID().toString().replace("-", "");
    	String fileName = disposition.getFileName();
    	String fileExt = fileName.substring(fileName.lastIndexOf("."));
    	String imageName = uuid + fileExt;
        Date dt=new Date();
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMMdd");

        String staticFile = type +"/"+ matter.format(dt)+"/"+imageName;

        File file = new File(ARTICLE_IMAGES_PATH + staticFile);
        try {
            //使用common io的文件写入操作
            FileUtils.copyInputStreamToFile(fileInputStream, file);

        }catch (IOException ex) {
            throw new WebActionException(WebExceptionType.UPLOADINVALIDIMG,imageName);
        }

        return staticFile;
    }

}
