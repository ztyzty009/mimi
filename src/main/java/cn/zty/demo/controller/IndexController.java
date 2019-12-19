package cn.zty.demo.controller;

import cn.zty.demo.model.User;
import cn.zty.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user =  userRepository.findByToken(token);
                if (user !=null){
                    request.getSession().setAttribute("user",user);
                }

                System.out.println(user.getName());
                break;
            }
        }



        return "index";


    }
}
