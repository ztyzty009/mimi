package cn.zty.demo.mapper;

import cn.zty.demo.model.Comment;
import cn.zty.demo.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int intCommentCount(Comment comment);
}