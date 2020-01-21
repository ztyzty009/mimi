package cn.zty.demo.controller;

import cn.zty.demo.dto.FileDTO;
import cn.zty.demo.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        FileUploadUtil util = new FileUploadUtil();
        String name = null;
        try {
            name = util.uploadFile2Cos(file);
            String url = util.getImgUrl(name);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/1.jpg");
        return fileDTO;
    }
}