package cn.zty.demo.service;

import cn.zty.demo.enums.CommentTypeEnum;
import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import cn.zty.demo.mapper.CommentMapper;
import cn.zty.demo.mapper.QuestionExtMapper;
import cn.zty.demo.mapper.QuestionMapper;
import cn.zty.demo.model.Comment;
import cn.zty.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional
    public void insert(Comment comment) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeEception(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeEception(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeEception(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复内容
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeEception(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.intCommentCount(question);
        }
    }
}
