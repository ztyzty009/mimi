package cn.zty.demo.controller;

import cn.zty.demo.dto.PaginationDTO;
import cn.zty.demo.model.User;
import cn.zty.demo.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    private Questionservice questionservice;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size

    ) {
        User user = (User) request.getSession().getAttribute("user");

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationDTO = questionservice.list(user.getId(), page, size);

        model.addAttribute("pagination",paginationDTO);

        return "profile";
    }
}
