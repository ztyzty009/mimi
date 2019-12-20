package cn.zty.demo.controller;

import cn.zty.demo.dto.QuestionDTO;
import cn.zty.demo.model.Question;
import cn.zty.demo.model.User;
import cn.zty.demo.repository.QuestionRepository;
import cn.zty.demo.repository.UserRepository;
import cn.zty.demo.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private Questionservice questionservice;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size

    ){

        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0)
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user =  userRepository.findByToken(token);
                    if (user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }

        List<QuestionDTO> questionList = questionservice.list();
        model.addAttribute("questionList",questionList);

        return "index";


    }
}
