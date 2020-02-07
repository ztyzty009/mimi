package cn.zty.demo.utils;

import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;


public class FileUploadUtil {

    public static COSCredentials cred = new BasicCOSCredentials("AKIDlzwfqmAyDE9pEQw0vTklBMIyXqtNqMIV","m8F7j5CXdV8WhIEvOcaIkbDdoslmKdkc" );
    public static ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
    public static COSClient cosClient = new COSClient(cred, clientConfig);
    public static String bucketName = "ztyzty-1301151586";
    private COSClient cOSClient;
    //无参构造
    public FileUploadUtil() {
        cOSClient = new COSClient(cred, clientConfig);
    }
    //销毁
    public void destory() {
        cOSClient.shutdown();
    }
    //上传图片
    public void uploadImg2Cos(String url) throws Exception {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2Cos(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new Exception("图片上传失败");
        }
    }
    //上传图片
    public String uploadFile2Cos(MultipartFile file) throws Exception {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new CustomizeEception(CustomizeErrorCode.UPLOAD_IMAGE_TOO_BIG);
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2Cos(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new CustomizeEception(CustomizeErrorCode.IMAGE_UPLOAD_FAILED);
        }
    }
    //获得图片路径
    public String getImgUrl(String fileUrl) {
        return getUrl(fileUrl);
    }
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    //上传到COS服务器 如果同名文件会覆盖服务器上的
    public String uploadFile2Cos(InputStream inputStream, String fileName) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = cOSClient.putObject(bucketName,  fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new CustomizeEception(CustomizeErrorCode.IMAGE_UPLOAD_FAILED);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                throw new CustomizeEception(CustomizeErrorCode.IMAGE_UPLOAD_FAILED);
            }
        }
        return ret;
    }
    // 判断Cos服务文件上传时文件的contentType
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }


}
