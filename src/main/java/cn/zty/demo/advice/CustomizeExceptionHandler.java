package cn.zty.demo.advice;

import cn.zty.demo.exception.CustomizeEception;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice()
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler( Throwable e, Model model){
        if (e instanceof CustomizeEception){
            model.addAttribute("message",e.getMessage());
        }else {
            //model.addAttribute("message","服务器冒烟了，要不稍后再试试！！");
        }
        return new ModelAndView("error");
    }
}
