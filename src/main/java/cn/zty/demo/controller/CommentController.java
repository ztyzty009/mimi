package cn.zty.demo.controller;

import cn.zty.demo.dto.CommentCreateDTO;
import cn.zty.demo.dto.CommentDTO;
import cn.zty.demo.dto.ResultDTO;
import cn.zty.demo.enums.CommentTypeEnum;
import cn.zty.demo.exception.CustomizeErrorCode;
import cn.zty.demo.mapper.CommentMapper;
import cn.zty.demo.model.Comment;
import cn.zty.demo.model.User;
import cn.zty.demo.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    private Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                        HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null|| StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setLikeCount(0l);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargeId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
