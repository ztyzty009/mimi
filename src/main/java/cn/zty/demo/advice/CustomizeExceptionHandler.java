package cn.zty.demo.advice;

import cn.zty.demo.dto.ResultDTO;
import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice()
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response){

        String contentype = request.getContentType();
        if ("application/json".equals(contentype)){
            ResultDTO resultDTO = null;
            if (e instanceof CustomizeEception){
                resultDTO= ResultDTO.errorOf((CustomizeEception) e);
            }else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            //返回json
            return null;
        }else {
            //跳转错误页面
            if (e instanceof CustomizeEception){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
