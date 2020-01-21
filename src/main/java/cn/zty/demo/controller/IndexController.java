package cn.zty.demo.controller;

import cn.zty.demo.dto.PaginationDTO;
import cn.zty.demo.model.User;
import cn.zty.demo.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private Questionservice questionservice;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "6")Integer size

    ){
        User user = (User) request.getSession().getAttribute("user");
        PaginationDTO pagination = questionservice.list(page, size);
        model.addAttribute("pagination",pagination);

        return "index";


    }
}
