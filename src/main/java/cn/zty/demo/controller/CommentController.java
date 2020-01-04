package cn.zty.demo.controller;

import cn.zty.demo.dto.CommentDTO;
import cn.zty.demo.dto.ResultDTO;
import cn.zty.demo.exception.CustomizeErrorCode;
import cn.zty.demo.mapper.CommentMapper;
import cn.zty.demo.model.Comment;
import cn.zty.demo.model.User;
import cn.zty.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    private Object post(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0l);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
