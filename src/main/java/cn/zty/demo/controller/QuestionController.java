package cn.zty.demo.controller;

import cn.zty.demo.dto.CommentDTO;
import cn.zty.demo.dto.QuestionDTO;
import cn.zty.demo.enums.CommentTypeEnum;
import cn.zty.demo.service.CommentService;
import cn.zty.demo.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private Questionservice questionservice;
    @Autowired
    private CommentService commentService;
    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id")long id, Model model){
        QuestionDTO questionDTO = questionservice.getById(id);
        List<QuestionDTO> relatedQuestions= questionservice.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargeId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionservice.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
